package com.example.goodsdisplay.data.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResponseCallAdapter(private val responseType: Type) :
    CallAdapter<Type, Call<Result<Type>>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<Type>): Call<Result<Type>> = ResponseCall(call)

    class Factory : CallAdapter.Factory() {
        override fun get(
            returnType: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            if (getRawType(returnType) != Call::class.java) return null
            check(returnType is ParameterizedType) {
                "Return type must be parameterized as Call<?>"
            }

            val responseType = getParameterUpperBound(0, returnType)
            if (getRawType(responseType) != Result::class.java) {
                return null
            }
            check(responseType is ParameterizedType) {
                "responseType must be parameterized as Result<?>"
            }

            val bodyType = getParameterUpperBound(0, responseType)
            return ResponseCallAdapter(bodyType)
        }
    }
}