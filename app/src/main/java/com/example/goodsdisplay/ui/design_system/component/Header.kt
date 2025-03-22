package com.example.goodsdisplay.ui.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodsdisplay.ui.design_system.theme.Gray800
import com.example.goodsdisplay.ui.design_system.theme.Typography
import com.example.goodsdisplay.ui.design_system.theme.White

@Composable
fun Header(
    title: String,
    icon: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .background(White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Title + Icon
        val iconId = "icon"
        val annotatedTitle = buildAnnotatedString {
            append(title)
            if (icon != null) {
                appendInlineContent(id = iconId, alternateText = "[Icon]")
            }
        }
        val titleInlineContent = icon?.let { icon ->
            mapOf(
                "icon" to InlineTextContent(
                    placeholder = Placeholder(24.sp, 24.sp, PlaceholderVerticalAlign.Center)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(3.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        icon()
                    }
                }
            )
        } ?: mapOf()

        Text(
            text = annotatedTitle,
            modifier = Modifier.weight(1f),
            color = Gray800,
            style = Typography.bodyLarge,
            inlineContent = titleInlineContent,
        )
        Spacer(Modifier.width(16.dp))

        // Trailing
        trailing?.invoke()
    }
}

@Preview
@Composable
private fun HeaderPreview() {
    Header(
        title = "Title",
        icon = {
            Icon(imageVector = Icons.Filled.Info, contentDescription = "Info")
        },
        trailing = {
            TextButton(text = "Text")
        }
    )
}

@Preview(fontScale = 2f)
@Composable
private fun HeaderBigFontScalePreview() {
    Header(
        title = "Title",
        icon = {
            Icon(imageVector = Icons.Filled.Info, contentDescription = "Info")
        },
        trailing = {
            TextButton(text = "Text")
        }
    )
}