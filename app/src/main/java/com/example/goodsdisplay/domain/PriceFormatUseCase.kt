package com.example.goodsdisplay.domain

import android.content.Context
import com.example.goodsdisplay.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PriceFormatUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    operator fun invoke(price: Int): String = context.getString(R.string.price_format, price)
}