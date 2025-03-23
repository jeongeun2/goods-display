package com.example.goodsdisplay.data.adapter

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseCall<T>(private val call: Call<T>) : Call<Result<T>> {
    override fun clone(): Call<Result<T>> = ResponseCall(call.clone())

    override fun execute(): Response<Result<T>> =
        Response.success(call.execute().toResult())

    override fun enqueue(callback: Callback<Result<T>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback.onResponse(
                    this@ResponseCall,
                    Response.success(response.toResult())
                )
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(this@ResponseCall, Response.success(Result.failure(t)))
            }
        })
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()

    private fun Response<T>.toResult(): Result<T> =
        try {
            if (isSuccessful) {
                body()?.let { Result.success(it) }
                    ?: Result.failure(Throwable("Response body is null"))
            } else {
                Result.failure(Throwable("HTTP Error code: ${code()}, message: ${message()}"))
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
}