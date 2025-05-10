package com.example.exp2_119

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var expressionField: EditText
    private lateinit var resultField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize EditText fields
        expressionField = findViewById(R.id.expression)
        resultField = findViewById(R.id.result)

        // Map buttons to their respective values
        val buttons = mapOf(
            R.id.button to "1", R.id.button2 to "2", R.id.button3 to "3", R.id.button4 to "+",
            R.id.button5 to "4", R.id.button6 to "5", R.id.button7 to "6", R.id.button8 to "-",
            R.id.button9 to "7", R.id.button10 to "8", R.id.button11 to "9", R.id.button12 to "*",
            R.id.button13 to "%", R.id.button15 to "0", R.id.button14 to "/", R.id.button16 to ".",
            R.id.button19 to "^"
        )

        // Assign click listeners to number and operator buttons
        buttons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener {
                expressionField.append(value)
            }
        }

        // Clear Button
        findViewById<Button>(R.id.button17).setOnClickListener {
            expressionField.text.clear()
            resultField.text.clear()
        }

        // Backspace Button (Removes last character)
        findViewById<Button>(R.id.button20)?.setOnClickListener {
            val text = expressionField.text.toString()
            if (text.isNotEmpty()) {
                expressionField.setText(text.dropLast(1))
                expressionField.setSelection(expressionField.text.length)  // Move cursor to end
            }
        }

        // Equal Button (Calculates result)
        findViewById<Button>(R.id.button18).setOnClickListener {
            val expressionText = expressionField.text.toString()
            try {
                val result = ExpressionBuilder(expressionText).build().evaluate()
                resultField.setText(result.toString())
            } catch (e: Exception) {
                resultField.setText("Error")
            }
        }
    }
}
