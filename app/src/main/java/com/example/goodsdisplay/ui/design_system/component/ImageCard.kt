package com.example.goodsdisplay.ui.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.goodsdisplay.ui.design_system.theme.Gray300
import com.example.goodsdisplay.ui.design_system.theme.Gray400
import com.example.goodsdisplay.ui.design_system.theme.Gray800
import com.example.goodsdisplay.ui.design_system.theme.Red
import com.example.goodsdisplay.ui.design_system.theme.Typography
import com.example.goodsdisplay.ui.design_system.theme.White

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    body: String,
    subBody: String,
    subBodyColor: Color = Red,
    indicator: (@Composable () -> Unit)? = null,
    indicatorAlignment: Alignment = Alignment.BottomStart,
) {
    Column(modifier.background(White)) {
        // Image + Indicator
        Box(Modifier.fillMaxWidth()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                placeholder = ColorPainter(Gray300),
            )
            indicator?.let {
                Box(Modifier.align(indicatorAlignment)) { it() }
            }
        }

        Column(Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
            // Title
            Text(
                text = title,
                color = Gray400,
                style = Typography.bodyLarge
            )
            Spacer(Modifier.height(8.dp))

            // Body + SubBody
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = body,
                    color = Gray800,
                    style = Typography.bodyLarge
                )
                Text(
                    text = subBody,
                    color = subBodyColor,
                    style = Typography.bodyLarge
                )
            }
        }
    }
}

@Preview(widthDp = 150)
@Composable
private fun ImageCardPreview() {
    ImageCard(
        imageUrl = "",
        title = "Title",
        body = "Body",
        subBody = "SubBody",
    )
}

@Preview(widthDp = 150, fontScale = 2f)
@Composable
private fun ImageCardBigFontScalePreview() {
    ImageCard(
        imageUrl = "",
        title = "Title",
        body = "Body",
        subBody = "SubBody",
    )
}