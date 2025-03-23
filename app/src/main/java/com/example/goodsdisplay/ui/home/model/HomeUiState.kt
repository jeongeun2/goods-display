package com.example.goodsdisplay.ui.home.model

sealed class HomeUiState {
    data object Loading : HomeUiState()

    data class Success(val contents: List<ContentsUiModel>) : HomeUiState()

    data class Error(val message: String) : HomeUiState()
}
