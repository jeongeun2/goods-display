package com.example.goodsdisplay.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.goodsdisplay.R
import com.example.goodsdisplay.ui.design_system.theme.Blue
import com.example.goodsdisplay.ui.design_system.theme.Typography
import com.example.goodsdisplay.ui.design_system.theme.White

@Composable
fun CouponIndicator() {
    Text(
        text = stringResource(R.string.coupon),
        modifier = Modifier
            .background(color = Blue)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        color = White,
        style = Typography.bodySmall,
    )
}