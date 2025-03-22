package com.example.goodsdisplay.ui.home.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.goodsdisplay.ui.design_system.component.ImageCard
import com.example.goodsdisplay.ui.home.model.Content

@Composable
fun GoodsVerticalGrid(goods: List<Content.Goods>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxWidth(),
    ) {
        itemsIndexed(goods) { _, goods ->
            ImageCard(
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
private fun GoodsVerticalGridPreview() {
    val goods = Content.Goods(
        imageUrl = "",
        brand = "아스트랄 프로젝션",
        price = "39,900원",
        saleRate = "50%",
        visibleCoupon = false,
    )
    GoodsVerticalGrid(
        goods = listOf(
            goods,
            goods.copy(visibleCoupon = true),
            goods,
            goods,
            goods.copy(visibleCoupon = true),
        )
    )
}