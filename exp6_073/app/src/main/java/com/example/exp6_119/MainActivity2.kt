package com.example.exp6_119

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etCode: EditText
    private lateinit var btnValidate: Button
    private lateinit var btnReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        etName = findViewById(R.id.etName)
        etCode = findViewById(R.id.etCode)
        btnValidate = findViewById(R.id.btnValidate)
        btnReset = findViewById(R.id.btnReset)

        btnValidate.setOnClickListener {
            validateUser()
        }

        btnReset.setOnClickListener {
            etName.text.clear()
            etCode.text.clear()
        }
    }

    private fun validateUser() {
        val name = etName.text.toString().trim()
        val code = etCode.text.toString().trim()

        when {
            name.isEmpty() || code.isEmpty() -> {
                showToast("Please fill out all fields")
            }
            !name.matches(Regex("^[A-Za-z]+\$")) -> {
                showToast("Name must contain only alphabets")
            }
            !code.matches(Regex("^\\d{4}\$")) -> {
                showToast("Code must be a 4-digit number")
            }
            else -> {
                showToast("Validation Successful!")
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
