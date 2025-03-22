package com.example.goodsdisplay.ui.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.goodsdisplay.R
import com.example.goodsdisplay.data.model.ContentsType
import com.example.goodsdisplay.ui.design_system.component.BoxButton
import com.example.goodsdisplay.ui.design_system.component.Header
import com.example.goodsdisplay.ui.design_system.component.TextButton
import com.example.goodsdisplay.ui.home.model.Content
import com.example.goodsdisplay.ui.home.model.ContentsUiModel
import com.example.goodsdisplay.ui.home.model.Footer
import com.example.goodsdisplay.ui.home.model.Header

@Composable
fun ContentsLayout(
    contentsUiModel: ContentsUiModel,
    onClickFooter: () -> Unit,
) {
    Column {
        // Header
        contentsUiModel.header?.let { ContentsHeader(header = it) }

        // Contents
        when (contentsUiModel.type) {
            ContentsType.BANNER -> {
                BannerPager(contentsUiModel.contents.filterIsInstance<Content.Banner>())
            }

            ContentsType.GRID -> {
                GoodsVerticalGrid(contentsUiModel.contents.filterIsInstance<Content.Goods>())
            }

            ContentsType.SCROLL -> {
                GoodsHorizontalScroller(contentsUiModel.contents.filterIsInstance<Content.Goods>())
            }

            ContentsType.STYLE -> {
                StylesVerticalGrid(contentsUiModel.contents.filterIsInstance<Content.Style>())
            }
        }
        Spacer(Modifier.height(16.dp))

        // Footer
        contentsUiModel.footer?.let {
            ContentsFooter(footer = it, onClickFooter = onClickFooter)
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ContentsHeader(header: Header) {
    Header(
        title = header.title,
        icon = header.iconUrl?.let { iconUrl ->
            { AsyncImage(model = iconUrl, contentDescription = "Icon") }
        },
        trailing = header.linkUrl?.let {
            { TextButton(text = stringResource(R.string.all)) }
        }
    )
}

@Composable
private fun ContentsFooter(footer: Footer, onClickFooter: () -> Unit) {
    Box(Modifier.padding(horizontal = 16.dp)) {
        BoxButton(
            text = footer.title,
            onClick = onClickFooter,
            icon = footer.iconUrl?.let { iconUrl ->
                { AsyncImage(model = iconUrl, contentDescription = "Icon") }
            },
        )
    }
}
