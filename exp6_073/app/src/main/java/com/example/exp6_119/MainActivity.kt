package com.example.exp6_119

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etID: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.etUsername)
        etID = findViewById(R.id.etID)
        btnLogin = findViewById(R.id.btnLogin)
        btnClear = findViewById(R.id.btnClear)

        btnLogin.setOnClickListener {
            validateInput()
        }

        btnClear.setOnClickListener {
            etUsername.text.clear()
            etID.text.clear()
        }
    }

    private fun validateInput() {
        val username = etUsername.text.toString().trim()
        val id = etID.text.toString().trim()

        when {
            username.isEmpty() || id.isEmpty() -> {
                showToast("Fields cannot be empty.")
            }
            !username.matches(Regex("^[A-Za-z]+\$")) -> {
                showToast("Invalid User Name")
            }
            !id.matches(Regex("^\\d{4}\$")) -> {
                showToast("ID must be exactly 4 digits")
            }
            else -> {
                showToast("Login Successful!")
                // Navigate to MainActivity2 only after successful validation
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
