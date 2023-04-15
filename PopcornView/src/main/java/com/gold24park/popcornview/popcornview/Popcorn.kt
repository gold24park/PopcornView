package com.gold24park.popcornview.popcornview

import android.graphics.Canvas
import android.graphics.Paint
import kotlin.math.cos
import kotlin.math.sin


data class Popcorn(
    private val shape: PopcornShape,
    private var position: Vector2D,
    private val initialVelocity: Float,
    private var angle: Float,
    private val elasticity: Float,
    private val friction: Float,
    private val ttl: Long,
    private val fadeout: Boolean,
) {
    private val velocity: Vector2D = Vector2D(
        cos(angle) * initialVelocity,
        sin(angle) * initialVelocity
    )

    private val created: Long = System.currentTimeMillis()

    private val age: Long
        get() = (System.currentTimeMillis() - created).coerceAtMost(ttl)

    private val paint = Paint()
        get() {
            if (fadeout) {
                val ageProgress = age.toFloat() / ttl
                field.alpha = ((1 - ageProgress) * 255).toInt()   
            }
            return field
        }

    private val right: Float
        get() = position.x + (shape.size / 2)
    private val left: Float
        get() = position.x - (shape.size / 2)
    private val top: Float
        get() = position.y - (shape.size / 2)
    private val bottom: Float
        get() = position.y + (shape.size / 2)

    fun draw(canvas: Canvas) {
        shape.draw(
            canvas,
            paint,
            x = position.x,
            y = position.y,
            rotation = Math.toDegrees(angle.toDouble()).toFloat()
        )
    }

    fun move(gravity: Float) {
        angle += 0.01F * velocity.x
        velocity.y += gravity
        position.add(velocity)
    }

    fun hitWall(canvas: Canvas) {
        val hitLeft = left < 0
        val hitRight = right > canvas.width
        val hitTop = top < 0

        val hit = hitLeft || hitRight || hitTop
        if (hit) {
            velocity.y *= elasticity
            velocity.x -= velocity.x * friction
        }

        when {
            hitLeft -> {
                position.x = shape.size / 2F
                velocity.x *= -1
            }
            hitRight -> {
                position.x = canvas.width - (shape.size / 2F)
                velocity.x *= -1
            }
            hitTop -> {
                position.y = shape.size / 2F
                velocity.y *= -1
            }
        }
    }

    fun isDoneRendering(canvas: Canvas): Boolean {
        return age == ttl ||
            right.toInt() > canvas.width && bottom > canvas.height
    }
}