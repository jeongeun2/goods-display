package com.example.goodsdisplay.domain

import javax.inject.Inject

class SaleRateFormatUseCase @Inject constructor() {
    operator fun invoke(saleRate: Int): String = "$saleRate%"
}