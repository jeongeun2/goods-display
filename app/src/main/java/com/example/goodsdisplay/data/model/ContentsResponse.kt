package com.example.goodsdisplay.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentsResponse(
    val data: List<Data>,
)

@JsonClass(generateAdapter = true)
data class Data(
    val contents: ContentsDto,
    val header: HeaderDto?,
    val footer: FooterDto?,
)

@JsonClass(generateAdapter = true)
data class ContentsDto(
    val type: ContentsType,
    val banners: List<BannerDto>?,
    val goods: List<GoodsDto>?,
    val styles: List<StyleDto>?,
)

@JsonClass(generateAdapter = true)
data class HeaderDto(
    val title: String,
    val iconURL: String?,
    val linkURL: String?,
)

@JsonClass(generateAdapter = true)
data class FooterDto(
    val type: FooterType,
    val title: String,
    val iconURL: String?,
)

@JsonClass(generateAdapter = true)
data class BannerDto(
    val linkURL: String,
    val thumbnailURL: String,
    val title: String,
    val description: String,
    val keyword: String
)

@JsonClass(generateAdapter = true)
data class GoodsDto(
    val linkURL: String,
    val thumbnailURL: String,
    val brandName: String,
    val price: Int,
    val saleRate: Int,
    val hasCoupon: Boolean
)

@JsonClass(generateAdapter = true)
data class StyleDto(
    val linkURL: String,
    val thumbnailURL: String
)
