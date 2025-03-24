package com.example.goodsdisplay.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goodsdisplay.R
import com.example.goodsdisplay.ui.design_system.component.BoxButton
import com.example.goodsdisplay.ui.design_system.theme.Red

@Composable
fun ErrorContent(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "Error Icon",
            tint = Red,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.error_message),
        )
        Spacer(modifier = Modifier.height(16.dp))
        BoxButton(
            modifier = Modifier
                .height(36.dp),
            text = stringResource(R.string.retry),
            onClick = onRetry
        )
    }
}

@Preview
@Composable
private fun ErrorContentPreview() {
    ErrorContent { }
}