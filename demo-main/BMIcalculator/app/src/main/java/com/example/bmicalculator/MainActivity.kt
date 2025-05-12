package com.example.bmicalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etHeight = findViewById<EditText>(R.id.etHeight)
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnCalculate.setOnClickListener {
            val heightStr = etHeight.text.toString()
            val weightStr = etWeight.text.toString()

            if (heightStr.isEmpty() || weightStr.isEmpty()) {
                Toast.makeText(this, "Please enter both height and weight", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val height = heightStr.toFloat() / 100  // convert cm to meters
            val weight = weightStr.toFloat()

            val bmi = weight / (height * height)

            val category = when {
                bmi < 18.5 -> "Underweight"
                bmi in 18.5..24.9 -> "Normal weight"
                bmi in 25.0..29.9 -> "Overweight"
                else -> "Obese"
            }

            tvResult.text = "BMI: %.2f\nCategory: %s".format(bmi, category)
        }
    }
}
