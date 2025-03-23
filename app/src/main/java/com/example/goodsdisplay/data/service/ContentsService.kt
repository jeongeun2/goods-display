package com.example.goodsdisplay.data.service

import com.example.goodsdisplay.data.model.ContentsResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import javax.inject.Singleton

interface ContentsService {
    @GET("interview/list.json")
    suspend fun getContents(): Result<ContentsResponse>
}

@Module
@InstallIn(SingletonComponent::class)
object ContentsServiceModule {
    @Provides
    @Singleton
    fun provideContentsService(retrofit: Retrofit): ContentsService = retrofit.create()
}
