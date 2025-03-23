package com.example.goodsdisplay.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodsdisplay.data.repository.ContentsRepository
import com.example.goodsdisplay.ui.home.model.HomeUiState
import com.example.goodsdisplay.ui.home.model.HomeUiStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: ContentsRepository,
    private val homeUiStateMapper: HomeUiStateMapper,
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadContents()
    }

    private fun loadContents() {
        viewModelScope.launch {
            _uiState.update {
                homeUiStateMapper.toUiState(homeRepository.getContents())
            }
        }
    }
}