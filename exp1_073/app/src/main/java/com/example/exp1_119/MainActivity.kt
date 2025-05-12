package com.example.exp1_119

import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.text1)
        val mainLayout = findViewById<RelativeLayout>(R.id.main)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)

//        val newTypeface = ResourcesCompat.getFont(this, R.font.newfont)
        button1.setOnClickListener {
//            textView.typeface = newTypeface
            textView.textSize = 30f;
            Toast.makeText(this, "Welcome to REC!", Toast.LENGTH_SHORT).show()
        }
        button2.setOnClickListener {
            textView.setTextColor(resources.getColor(R.color.red, null))
            Toast.makeText(this, "Welcome to REC!", Toast.LENGTH_SHORT).show()
        }
        button3.setOnClickListener {
            mainLayout.setBackgroundResource(R.drawable.bg)
            Toast.makeText(this, "Welcome to REC!", Toast.LENGTH_SHORT).show()
        }
    }
}