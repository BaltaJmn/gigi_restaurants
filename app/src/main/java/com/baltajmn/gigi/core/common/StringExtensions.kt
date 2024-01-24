package com.baltajmn.gigi.core.common

fun String.showDistance(): String {
    val distance = this.toDouble()
    return when {
        distance < 1 -> "${(distance * 1000).toInt()} m"
        else -> "${distance.toInt()} km"
    }
}