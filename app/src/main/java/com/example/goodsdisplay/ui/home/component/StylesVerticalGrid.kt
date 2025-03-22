package com.example.goodsdisplay.ui.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.goodsdisplay.ui.design_system.theme.Gray300
import com.example.goodsdisplay.ui.home.model.Content

@Composable
fun StylesVerticalGrid(styles: List<Content.Style>) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val basicItemWidth = screenWidth / 3
    val basicItemHeight = basicItemWidth * 7 / 5 // width / height 5:7 비율
    val firstDisplaySize = 3

    Column {
        // 첫번째 아이템 2x2로 표시
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(basicItemHeight * 2),
        ) {
            itemsIndexed(
                items = styles.take(firstDisplaySize),
                span = { index, _ ->
                    if (index == 0) GridItemSpan(2) else GridItemSpan(1)
                }
            ) { index, style ->
                StyleCard(
                    style = style,
                    width = if (index == 0) basicItemWidth * 2 else basicItemWidth,
                )
            }
        }

        if (styles.size > firstDisplaySize) {
            val size = styles.size - firstDisplaySize
            val rows = (size / 3 + if (size % 3 > 0) 1 else 0)
            val height = basicItemHeight * rows

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height),
            ) {
                itemsIndexed(styles.drop(firstDisplaySize)) { _, style ->
                    StyleCard(
                        style = style,
                        width = basicItemWidth,
                    )
                }
            }
        }
    }
}

@Composable
private fun StyleCard(style: Content.Style, width: Dp) {
    AsyncImage(
        model = style.imageUrl,
        contentDescription = "Style Image",
        modifier = Modifier
            .width(width),
        placeholder = ColorPainter(Gray300),
    )
}

@Preview
@Composable
private fun StylesVerticalGridPreview() {
    val style = Content.Style("", "")
    StylesVerticalGrid(
        styles = listOf(style, style, style, style, style, style, style)
    )
}