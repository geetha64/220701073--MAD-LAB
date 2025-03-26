package com.example.graphicalprimitives

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        textSize = 50f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw a Circle
        paint.color = Color.RED
        canvas.drawCircle(200f, 200f, 100f, paint)

        // Draw a Rectangle
        paint.color = Color.BLUE
        canvas.drawRect(100f, 400f, 300f, 600f, paint)

        // Draw an Ellipse
        paint.color = Color.GREEN
        canvas.drawOval(100f, 700f, 300f, 900f, paint)

        // Draw Text
        paint.color = Color.BLACK
        canvas.drawText("Hello, World!", 100f, 1050f, paint)
    }
}
