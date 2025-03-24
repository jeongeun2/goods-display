package com.example.goodsdisplay.ui.home.model

import com.example.goodsdisplay.data.model.ContentsDto
import com.example.goodsdisplay.data.model.ContentsResponse
import com.example.goodsdisplay.data.model.Data
import com.example.goodsdisplay.data.model.FooterType
import com.example.goodsdisplay.domain.PriceFormatUseCase
import com.example.goodsdisplay.domain.SaleRateFormatUseCase
import com.example.goodsdisplay.ui.home.model.HomeUiState.Error
import com.example.goodsdisplay.ui.home.model.HomeUiState.Success
import javax.inject.Inject

class HomeUiStateMapper @Inject constructor(
    private val priceFormatUseCase: PriceFormatUseCase,
    private val saleRateFormatUseCase: SaleRateFormatUseCase,
) {
    fun toUiState(result: Result<ContentsResponse>): HomeUiState =
        result.getOrNull()?.let { response ->
            Success(response.data.mapIndexed { index, data -> data.toUiModel(index) })
        } ?: Error

    fun toUiStateWithInitialLimit(contentsUiModels: List<ContentsUiModel>): HomeUiState =
        Success(contentsUiModels.map {
            if (it.footer?.type == FooterType.MORE) {
                it.copy(contents = it.contents.take(it.type.columns * INITIAL_GRID_ROWS))
            } else {
                it
            }
        })

    private fun Data.toUiModel(id: Int): ContentsUiModel =
        ContentsUiModel(
            id = id,
            header = header?.let {
                Header(it.title, it.iconURL, it.linkURL)
            },
            type = contents.type,
            contents = contents.toUiModel(),
            footer = footer?.let {
                Footer(it.type, it.title, it.iconURL)
            },
        )

    private fun ContentsDto.toUiModel(): List<Content> =
        banners?.map {
            Content.Banner(
                imageUrl = it.thumbnailURL,
                title = it.title,
                body = it.description,
                linkUrl = it.linkURL,
            )
        } ?: goods?.map {
            Content.Goods(
                imageUrl = it.thumbnailURL,
                brand = it.brandName,
                price = priceFormatUseCase(it.price),
                saleRate = saleRateFormatUseCase(it.saleRate),
                visibleCoupon = it.hasCoupon,
            )
        } ?: styles?.map {
            Content.Style(imageUrl = it.thumbnailURL, linkUrl = it.linkURL)
        } ?: emptyList()

    companion object {
        private const val INITIAL_GRID_ROWS = 2
    }
}