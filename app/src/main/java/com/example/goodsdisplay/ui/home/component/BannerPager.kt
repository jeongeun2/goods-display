package com.example.goodsdisplay.ui.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.goodsdisplay.ui.design_system.theme.Gray300
import com.example.goodsdisplay.ui.design_system.theme.Gray800
import com.example.goodsdisplay.ui.design_system.theme.Typography
import com.example.goodsdisplay.ui.design_system.theme.White
import com.example.goodsdisplay.ui.home.model.Content

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerPager(banners: List<Content.Banner>) {
    Box {
        val pagerState = rememberPagerState(pageCount = { banners.size })

        // Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            BannerContent(banners[page])
        }

        // Page Indicator
        Text(
            text = "${pagerState.currentPage + 1} / ${banners.size}",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(color = Gray800.copy(alpha = 0.5f))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            color = White,
        )
    }
}

@Composable
private fun BannerContent(banner: Content.Banner) {
    Box {
        // Image
        AsyncImage(
            model = banner.imageUrl,
            contentDescription = "Banner Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp),
            placeholder = ColorPainter(Gray300),
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = banner.title,
                color = White,
                style = Typography.titleLarge,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(8.dp))
            // Body
            Text(
                text = banner.body,
                color = White,
                style = Typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(36.dp))
        }
    }
}

@Preview
@Composable
private fun BannerPagerPreview() {
    BannerPager(
        listOf(
            Content.Banner(
                imageUrl = "",
                title = "하이드아웃 S/S 시즌오프",
                body = "최대 30% 할인",
                linkUrl = ""
            )
        )
    )
}

@Preview(fontScale = 2f)
@Composable
private fun BannerPagerBigFontScalePreview() {
    BannerPager(
        banners = listOf(
            Content.Banner(
                imageUrl = "",
                title = "슬로우 레코드 하우스 22 S/S",
                body = "스컬프터, 노이아고 외 최대 60% 할인",
                linkUrl = ""
            )
        )
    )
}