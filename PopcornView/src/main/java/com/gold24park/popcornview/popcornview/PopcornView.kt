package com.gold24park.popcornview.popcornview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class PopcornView : View {

    private var interval: Long = 0L
    private var amount: Int = 30
    private var createdAmount: Int = 0

    private val popcorns: MutableList<Popcorn> = mutableListOf()
    private var popcornShape: PopcornShape? = null
    private var lastPopcornCreated: Time = 0L
    private var startPosition = Vector2D()

    var gravity = 0.37F
    var minVelocity = 20
    var maxVelocity = 40
    var angleRangeStart = 45
    var angleRangeEnd = 135

    var elasticity: Float = 0.6F
    var friction: Float = 0.39F
    var ttl: Long = 30_000L
    var fadeout: Boolean = true

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setTypedArray(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setTypedArray(context, attrs)
    }

    private fun setTypedArray(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PopcornView)

        elasticity = typedArray.getFloat(R.styleable.PopcornView_popcornElasticity, elasticity)
        friction = typedArray.getFloat(R.styleable.PopcornView_popcornFriction, friction)
        ttl = typedArray.getInt(R.styleable.PopcornView_popcornTtl, ttl.toInt()).toLong()
        fadeout = typedArray.getBoolean(R.styleable.PopcornView_popcornFadeout, fadeout)
        minVelocity = typedArray.getInt(R.styleable.PopcornView_popcornMinVelocity, minVelocity)
        maxVelocity = typedArray.getInt(R.styleable.PopcornView_popcornMaxVelocity, maxVelocity)
        angleRangeStart =
            typedArray.getInt(R.styleable.PopcornView_popcornAngleRangeStart, angleRangeStart)
        angleRangeEnd =
            typedArray.getInt(R.styleable.PopcornView_popcornAngleRangeEnd, angleRangeEnd)
        gravity = typedArray.getFloat(R.styleable.PopcornView_popcornGravity, gravity)
        amount = typedArray.getInt(R.styleable.PopcornView_popcornGravity, amount)
        interval =
            typedArray.getInt(R.styleable.PopcornView_popcornGravity, interval.toInt()).toLong()

        typedArray.recycle()
    }

    fun start(
        shape: PopcornShape,
        startX: Float,
        startY: Float
    ) = start(shape, amount, interval, startX, startY)

    fun start(
        shape: PopcornShape,
        startPosition: StartPosition = StartPosition(
            xAxis = XAxisPosition.CENTER,
            yAxis = YAxisPosition.BOTTOM
        )
    ) = start(shape, amount, interval, startPosition)

    fun start(
        shape: PopcornShape,
        amount: Int,
        interval: Long,
        startPosition: StartPosition = StartPosition(
            xAxis = XAxisPosition.CENTER,
            yAxis = YAxisPosition.BOTTOM
        )
    ) {
        val vector = startPosition.getVector2D(width, height)
        start(shape, amount, interval, vector.x, vector.y)
    }

    fun start(
        shape: PopcornShape,
        amount: Int,
        interval: Long,
        startX: Float,
        startY: Float,
    ) {
        if (interval < 0)
            throw IllegalArgumentException("interval must be positive.")

        if (amount < 0)
            throw IllegalArgumentException("amount must be positive.")

        createdAmount = 0
        lastPopcornCreated = 0L

        popcornShape = shape
        this.amount = amount
        this.interval = interval
        this.startPosition = Vector2D(startX, startY)

        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (popcornShape == null) {
            return
        }

        if (createdAmount < amount && lastPopcornCreated.isElapsed(interval)) {
            popcorns.add(createPopcorn(position = startPosition.copy()))
            lastPopcornCreated = System.currentTimeMillis()
            createdAmount++
        }

        for (i in popcorns.size - 1 downTo 0) {
            val popcorn = popcorns[i]
            popcorn.move(gravity)
            popcorn.hitWall(canvas)
            popcorn.draw(canvas)

            if (popcorn.isDoneRendering(canvas)) {
                popcorns.removeAt(i)
            }
        }

        if (popcorns.isNotEmpty()) {
            invalidate()
        }
    }

    private fun createPopcorn(position: Vector2D): Popcorn {
        val velocity = (minVelocity..maxVelocity).random().toFloat()
        val angle = (angleRangeStart..angleRangeEnd).random().toFloat()
        return Popcorn(
            shape = popcornShape!!,
            position = position,
            initialVelocity = velocity,
            angle = angle,
            elasticity = elasticity,
            friction = friction,
            ttl = ttl,
            fadeout = fadeout
        )
    }

    /*
     * All popcorns will directly disappear from the view.
     */
    fun stop() {
        popcorns.clear()
    }

    /*
     * Stop popcorns generating.
     * When all active popcorns are done rendering, It'll be removed.
     */
    fun stopGracefully() {
        amount = 0
    }
}