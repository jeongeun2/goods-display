package com.example.goodsdisplay.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodsdisplay.data.repository.ContentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: ContentsRepository,
) : ViewModel() {

    init {
        loadContents()
    }

    private fun loadContents() {
        viewModelScope.launch {
            val contents = homeRepository.getContents()
            Log.d("HomeViewModel", "contents: $contents")
        }
    }
}