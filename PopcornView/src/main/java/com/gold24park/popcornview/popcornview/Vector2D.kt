package com.gold24park.popcornview.popcornview

import kotlin.math.sqrt

data class Vector2D(
    var x: Float = 0F,
    var y: Float = 0F
) {

    private val magnitude: Float
        get() = sqrt(x * x + y * y)

    fun normalize(): Vector2D {
        val m = magnitude
        this.x /= m
        this.y /= m
        return this
    }

    fun normalized(): Vector2D {
        val m = magnitude
        return Vector2D(this.x / m, this.y / m)
    }

    fun distPow(v: Vector2D) = (v.x - x) * (v.x - x) + (v.y - y) * (v.y - y)

    fun dist(v: Vector2D) = sqrt((v.x - x) * (v.x - x) + (v.y - y) * (v.y - y))

    fun multiply(scala: Float): Vector2D {
        this.x *= scala
        this.y *= scala
        return this
    }

    fun add(vector: Vector2D): Vector2D {
        this.x += vector.x
        this.y += vector.y
        return this
    }

    fun subtract(vector: Vector2D): Vector2D {
        this.x -= vector.x
        this.y -= vector.y
        return this
    }
}