package com.example.goodsdisplay.data.repository

import com.example.goodsdisplay.data.model.ContentsResponse
import com.example.goodsdisplay.data.service.ContentsService
import javax.inject.Inject

class ContentsRepository @Inject constructor(
    private val contentsService: ContentsService,
) {
    suspend fun getContents(): Result<ContentsResponse> = contentsService.getContents()
}