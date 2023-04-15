package com.gold24park.popcornview.popcornview

enum class XAxisPosition {
    LEFT, CENTER, RIGHT
}

enum class YAxisPosition {
    TOP, CENTER, BOTTOM
}

data class StartPosition(
    private val xAxis: XAxisPosition,
    private val yAxis: YAxisPosition
) {
    fun getVector2D(width: Int, height: Int): Vector2D {
        val vector = Vector2D()
        vector.x = when (xAxis) {
            XAxisPosition.LEFT -> 0F
            XAxisPosition.RIGHT -> width.toFloat()
            XAxisPosition.CENTER -> width / 2F
        }
        vector.y = when (yAxis) {
            YAxisPosition.TOP -> 0F
            YAxisPosition.BOTTOM -> height.toFloat()
            YAxisPosition.CENTER -> height / 2F
        }
        return vector
    }
}