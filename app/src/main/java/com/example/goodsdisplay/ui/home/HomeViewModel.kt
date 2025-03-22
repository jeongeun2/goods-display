package com.example.goodsdisplay.ui.home

import androidx.lifecycle.ViewModel
import com.example.goodsdisplay.data.repository.ContentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: ContentsRepository,
): ViewModel() {

}