package com.example.goodsdisplay.ui.home.model

import com.example.goodsdisplay.data.model.ContentsType
import com.example.goodsdisplay.data.model.FooterType

data class ContentsUiModel(
    val id: Int,
    val header: Header?,
    val type: ContentsType,
    val contents: List<Content>,
    val footer: Footer?,
)

data class Header(
    val title: String,
    val iconUrl: String?,
    val linkUrl: String?,
)

sealed class Content {
    data class Banner(
        val imageUrl: String,
        val title: String,
        val body: String,
        val linkUrl: String,
    ) : Content()

    data class Goods(
        val imageUrl: String,
        val brand: String,
        val price: String,
        val saleRate: String,
        val visibleCoupon: Boolean,
    ) : Content()

    data class Style(
        val imageUrl: String,
        val linkUrl: String,
    ) : Content()
}

data class Footer(
    val type: FooterType,
    val title: String,
    val iconUrl: String?,
)