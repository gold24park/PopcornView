package com.gold24park.popcornview.popcornview

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

sealed class PopcornShape(open val size: Float) {

    data class TextShape(
        override val size: Float,
        val text: String,
        val textColor: Int = Color.BLACK,
    ): PopcornShape(size) {
        override fun draw(
            canvas: Canvas,
            paint: Paint,
            x: Float,
            y: Float,
            rotation: Float
        ) {
            paint.isAntiAlias = true
            paint.textSize = size
            paint.textAlign = Paint.Align.CENTER
            paint.color = textColor
            canvas.rotate(rotation, x, y)
            canvas.drawText(text, x, y, paint)
            canvas.rotate(-rotation, x, y)
        }
    }

    data class DrawableShape(
        override val size: Float,
        private val drawable: Drawable
    ): PopcornShape(size) {

        private val bitmap: Bitmap = Bitmap.createScaledBitmap(
            (drawable as BitmapDrawable).bitmap,
            size.toInt(),
            size.toInt(),
            false
        )

        override fun draw(
            canvas: Canvas,
            paint: Paint,
            x: Float,
            y: Float,
            rotation: Float
        ) {
            val matrix = Matrix().apply {
                setRotate(
                    rotation,
                    size / 2,
                    size / 2
                )
                postTranslate(
                    x - (size / 2),
                    y - (size / 2)
                )
            }
            canvas.drawBitmap(bitmap, matrix, paint)
            matrix.reset()
        }
    }

    abstract fun draw(canvas: Canvas, paint: Paint, x: Float, y: Float, rotation: Float)
}