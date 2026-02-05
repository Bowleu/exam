package com.bowleu.exam.android

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.random.Random

class FillRectView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private var progress = 0

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.LTGRAY
        style = Paint.Style.FILL
    }

    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = randomColor()
        style = Paint.Style.FILL
    }

    init {
        setOnClickListener {
            increaseProgress()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), backgroundPaint)
        val fillWidth = width * (progress / 100f)
        canvas.drawRect(0f, 0f, fillWidth, height.toFloat(), fillPaint)
    }

    private fun increaseProgress() {
        progress += 10

        if (progress > 100) {
            progress = 0
        }
        Log.d("FillRectView", "progress = $progress")
        fillPaint.color = randomColor()
        invalidate()
    }

    private fun randomColor(): Int {
        return Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
    }
}