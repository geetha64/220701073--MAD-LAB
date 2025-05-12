package com.example.exp3_119

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(CustomView(this, null))
    }
}

class CustomView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Set background color to white
        canvas.drawColor(Color.WHITE)

        // Draw heading
        paint.color = Color.BLACK
        paint.textSize = 70f
        paint.isFakeBoldText = true
        canvas.drawText("Graphical Primitives", 50f, 100f, paint)

        // Set paint properties for shapes
        paint.style = Paint.Style.FILL

        // Draw Circle
        paint.color = Color.RED
        canvas.drawCircle(200f, 250f, 80f, paint)
        paint.color = Color.BLACK
        paint.textSize = 40f
        canvas.drawText("Circle", 170f, 360f, paint)

        // Draw Rectangle
        paint.color = Color.GREEN
        canvas.drawRect(400f, 180f, 700f, 350f, paint)
        paint.color = Color.BLACK
        canvas.drawText("Rectangle", 500f, 380f, paint)

        // Draw Square
        paint.color = Color.BLUE
        canvas.drawRect(150f, 450f, 300f, 600f, paint)
        paint.color = Color.BLACK
        canvas.drawText("Square", 180f, 630f, paint)

        // Draw Line
        paint.color = Color.BLACK
        paint.strokeWidth = 8f
        canvas.drawLine(450f, 500f, 450f, 650f, paint)
        canvas.drawText("Line", 430f, 700f, paint)
    }
}
