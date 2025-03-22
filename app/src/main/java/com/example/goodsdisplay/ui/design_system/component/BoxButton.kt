package com.example.goodsdisplay.ui.design_system.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodsdisplay.ui.design_system.theme.Blue
import com.example.goodsdisplay.ui.design_system.theme.Gray400
import com.example.goodsdisplay.ui.design_system.theme.Gray800
import com.example.goodsdisplay.ui.design_system.theme.White

enum class BoxButtonStyle {
    Outline, Filled
}

enum class BoxButtonIconPosition {
    Start, End
}

@Composable
fun BoxButton(
    text: String,
    onClick: () -> Unit = {},
    buttonStyle: BoxButtonStyle = BoxButtonStyle.Outline,
    icon: (@Composable () -> Unit)? = null,
    iconPosition: BoxButtonIconPosition = BoxButtonIconPosition.Start,
) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 54.dp)

    when (buttonStyle) {
        BoxButtonStyle.Filled -> {
            Button(
                onClick = onClick,
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(containerColor = Blue, contentColor = White)
            ) {
                ButtonContent(text, icon, iconPosition)
            }
        }

        BoxButtonStyle.Outline -> {
            OutlinedButton(
                onClick = onClick,
                modifier = buttonModifier,
                colors = ButtonDefaults.outlinedButtonColors(containerColor = White, contentColor = Gray800),
                border = BorderStroke(width = 1.dp, color = Gray400)
            ) {
                ButtonContent(text, icon, iconPosition)
            }
        }
    }
}

@Composable
private fun ButtonContent(
    text: String,
    icon: (@Composable () -> Unit)?,
    iconPosition: BoxButtonIconPosition,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (iconPosition == BoxButtonIconPosition.Start) {
            icon?.invoke()
            Text(text = text)
        } else {
            Text(text = text)
            icon?.invoke()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OutlineBoxButtonPreview() {
    BoxButton(
        text = "Text",
        buttonStyle = BoxButtonStyle.Outline,
        icon = {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "Refresh"
            )
        },
        iconPosition = BoxButtonIconPosition.End,
    )
}

@Preview
@Composable
private fun SolidBoxButtonPreview() {
    BoxButton(
        text = "Text",
        buttonStyle = BoxButtonStyle.Filled,
        icon = {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "Refresh"
            )
        },
        iconPosition = BoxButtonIconPosition.Start,
    )
}
