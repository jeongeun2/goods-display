package com.example.goodsdisplay.ui.home.model

import com.example.goodsdisplay.data.model.ContentsDto
import com.example.goodsdisplay.data.model.ContentsResponse
import com.example.goodsdisplay.data.model.Data
import com.example.goodsdisplay.ui.home.model.HomeUiState.Error
import com.example.goodsdisplay.ui.home.model.HomeUiState.Success
import javax.inject.Inject

class HomeUiStateMapper @Inject constructor() {
    fun toUiState(result: Result<ContentsResponse>): HomeUiState =
        result.getOrNull()?.let { response ->
            Success(response.data.map { it.toUiModel() })
        } ?: Error

    private fun Data.toUiModel(): ContentsUiModel =
        ContentsUiModel(
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
                price = it.price.toString(), // TODO formatting
                saleRate = it.saleRate.toString(), // TODO formatting
                visibleCoupon = it.hasCoupon,
            )
        } ?: styles?.map {
            Content.Style(imageUrl = it.thumbnailURL, linkUrl = it.linkURL)
        } ?: emptyList()
}