package com.him.eurohim.domain

fun Double.roundToMinStep(step: Double = 0.01): String {
    val roundedValue = (this / step).toInt() * step
    return String.format("%.2f", roundedValue)
}