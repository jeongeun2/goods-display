package com.example.goodsdisplay.ui.design_system.component

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodsdisplay.ui.design_system.theme.Gray600
import com.example.goodsdisplay.ui.design_system.theme.Typography

@Composable
fun TextButton(
    text: String,
    color: Color = Gray600,
) {
    Text(
        text = text,
        modifier = Modifier
            .heightIn(min = 24.dp)
            .padding(4.dp),
        color = color,
        style = Typography.bodyLarge,
    )
}

@Preview
@Composable
private fun TextButtonPreview() {
    TextButton(text = "Text")
}