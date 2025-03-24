package com.example.goodsdisplay.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goodsdisplay.data.model.FooterType
import com.example.goodsdisplay.ui.design_system.component.LoadingIndicator
import com.example.goodsdisplay.ui.home.component.ContentsLayout
import com.example.goodsdisplay.ui.home.component.ErrorContent
import com.example.goodsdisplay.ui.home.model.ContentsUiModel
import com.example.goodsdisplay.ui.home.model.HomeUiState

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is HomeUiState.Loading -> LoadingIndicator()
        is HomeUiState.Success -> ContentsContent(
            modifier = modifier,
            contents = (uiState as HomeUiState.Success).contents,
            onClickFooter = viewModel::onClickFooter
        )

        is HomeUiState.Error -> ErrorContent(onRetry = viewModel::loadContents)
    }
}

@Composable
private fun ContentsContent(
    modifier: Modifier,
    contents: List<ContentsUiModel>,
    onClickFooter: (id: Int, type: FooterType) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
    ) {
        contents.forEach { contentsUiModel ->
            ContentsLayout(
                contentsUiModel = contentsUiModel,
                onClickFooter = onClickFooter,
            )
        }
    }
}