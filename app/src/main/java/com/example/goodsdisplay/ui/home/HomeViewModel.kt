package com.example.goodsdisplay.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodsdisplay.data.model.FooterType
import com.example.goodsdisplay.data.repository.ContentsRepository
import com.example.goodsdisplay.ui.home.model.ContentsUiModel
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
    private lateinit var originalContentsUiModels: List<ContentsUiModel>
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadContents()
    }

    fun loadContents() {
        _uiState.update { HomeUiState.Loading }
        viewModelScope.launch {
            val uiState = homeUiStateMapper.toUiState(homeRepository.getContents())

            if (uiState is HomeUiState.Success) {
                originalContentsUiModels = uiState.contents
                _uiState.update {
                    homeUiStateMapper.toUiStateWithInitialLimit(originalContentsUiModels)
                }
            } else {
                _uiState.update { uiState }
            }
        }
    }

    fun onClickFooter(id: Int, type: FooterType) {
        when (type) {
            FooterType.MORE -> loadMore(id)
            FooterType.REFRESH -> shuffle(id)
        }
    }

    private fun loadMore(id: Int) {
        val originalUiModel = originalContentsUiModels.find { it.id == id } ?: return

        _uiState.update { currentUiState ->
            if (currentUiState !is HomeUiState.Success) return

            HomeUiState.Success(
                currentUiState.contents.map { currentUiModel ->
                    if (currentUiModel.id == id) {
                        val newCount =
                            currentUiModel.contents.size + currentUiModel.type.columns
                        val isEndOfData = newCount >= originalUiModel.contents.size
                        currentUiModel.copy(
                            contents = originalUiModel.contents.take(newCount),
                            footer = if (isEndOfData) null else currentUiModel.footer,
                        )
                    } else {
                        currentUiModel
                    }
                }
            )
        }
    }

    private fun shuffle(id: Int) {
        _uiState.update { currentUiState ->
            if (currentUiState !is HomeUiState.Success) return

            HomeUiState.Success(
                currentUiState.contents.map { currentUiModel ->
                    if (currentUiModel.id == id) {
                        currentUiModel.copy(contents = currentUiModel.contents.shuffled())
                    } else {
                        currentUiModel
                    }
                }
            )
        }
    }
}