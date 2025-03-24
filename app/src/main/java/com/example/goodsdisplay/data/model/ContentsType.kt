package com.example.goodsdisplay.data.model

enum class ContentsType(val columns: Int = Int.MAX_VALUE) {
    BANNER, GRID(3), SCROLL, STYLE(3)
}