package com.example.goodsdisplay.ui.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.goodsdisplay.ui.design_system.theme.Gray300
import com.example.goodsdisplay.ui.home.model.Content

@Composable
fun StylesVerticalGrid(styles: List<Content.Style>, columns: Int) {
    if (styles.isEmpty()) return

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val basicItemWidth = screenWidth / columns
    val basicItemHeight = basicItemWidth * 7 / 5 // width / height 5:7 비율
    val firstDisplaySize = 3
    val firstDisplayItems = styles.take(firstDisplaySize)

    Column {
        // 첫번째 아이템 2x2로 표시
        Row(
            Modifier
                .fillMaxWidth()
                .height(basicItemHeight * 2)
        ) {
            firstDisplayItems.firstOrNull()?.let { style ->
                Box(Modifier.weight(2f)) {
                    StyleCard(style)
                }
            }

            Column(Modifier.weight(1f)) {
                val lastSize = firstDisplaySize - 1
                val lastItems = firstDisplayItems.drop(1)

                lastItems.forEach { style ->
                    Box(Modifier.weight(1f)) {
                        StyleCard(style)
                    }
                }

                // 남은 공간
                repeat(lastSize - lastItems.size) {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                            .height(100.dp)
                    )
                }
            }
        }

        if (styles.size > firstDisplaySize) {
            VerticalGrid(items = styles.drop(firstDisplaySize), columns = columns) { style ->
                StyleCard(style)
            }
        }
    }
}

@Composable
private fun StyleCard(style: Content.Style) {
    AsyncImage(
        model = style.imageUrl,
        contentDescription = "Style Image",
        modifier = Modifier.fillMaxWidth(),
        placeholder = ColorPainter(Gray300),
        contentScale = ContentScale.Crop,
    )
}

@Preview
@Composable
private fun StylesVerticalGridPreview() {
    val style = Content.Style("", "")
    StylesVerticalGrid(
        styles = listOf(style, style, style, style, style, style, style),
        columns = 3
    )
}