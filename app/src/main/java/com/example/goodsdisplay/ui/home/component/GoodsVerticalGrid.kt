package com.example.goodsdisplay.ui.home.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.goodsdisplay.ui.design_system.component.ImageCard
import com.example.goodsdisplay.ui.home.model.Content

@Composable
fun GoodsVerticalGrid(goods: List<Content.Goods>, columns: Int) {
    VerticalGrid(items = goods, columns = columns) { item ->
        ImageCard(
            imageUrl = item.imageUrl,
            title = item.brand,
            body = item.price,
            subBody = item.saleRate,
            indicator = if (item.visibleCoupon) {
                { CouponIndicator() }
            } else {
                null
            }
        )
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
        ),
        columns = 3,
    )
}