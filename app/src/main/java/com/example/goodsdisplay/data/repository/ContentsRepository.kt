package com.example.goodsdisplay.data.repository

import com.example.goodsdisplay.data.service.ContentsService
import javax.inject.Inject

class ContentsRepository @Inject constructor(
    private val contentsService: ContentsService,
) {
}