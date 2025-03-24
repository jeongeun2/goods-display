package com.example.goodsdisplay.ui.home.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodsdisplay.ui.design_system.component.ImageCard
import com.example.goodsdisplay.ui.home.model.Content

@Composable
fun GoodsHorizontalScroller(goods: List<Content.Goods>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(goods) { goods ->
            ImageCard(
                modifier = Modifier.width(150.dp),
                imageUrl = goods.imageUrl,
                title = goods.brand,
                body = goods.price,
                subBody = goods.saleRate,
                indicator = if (goods.visibleCoupon) {
                    { CouponIndicator() }
                } else {
                    null
                }
            )
        }
    }
}

@Preview
@Composable
private fun GoodsHorizontalScrollerPreview() {
    val goods = Content.Goods(
        imageUrl = "",
        brand = "아스트랄 프로젝션",
        price = "39,900원",
        saleRate = "50%",
        visibleCoupon = false,
    )
    GoodsHorizontalScroller(
        goods = listOf(
            goods,
            goods.copy(visibleCoupon = true),
            goods,
        )
    )
}