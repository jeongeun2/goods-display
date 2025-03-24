package com.example.goodsdisplay.ui.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> VerticalGrid(items: List<T>, columns: Int = 3, itemContent: @Composable (T) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        items.chunked(columns).forEach { rowItems ->
            Row(Modifier.fillMaxWidth()) {
                rowItems.forEach { item ->
                    Box(Modifier.weight(1f)) {
                        itemContent(item)
                    }
                }

                // 남은 공간
                repeat(columns - rowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}