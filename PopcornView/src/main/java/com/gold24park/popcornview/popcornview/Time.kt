package com.gold24park.popcornview.popcornview

typealias Time = Long

fun Time.isElapsed(interval: Long): Boolean {
    return System.currentTimeMillis() - this > interval
}