package com.example.exp8_119

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextMessage: EditText
    private lateinit var buttonDisplay: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextMessage = findViewById(R.id.editTextMessage)
        buttonDisplay = findViewById(R.id.buttonDisplay)

        buttonDisplay.setOnClickListener {
            val message = editTextMessage.text.toString()

            val dialogBuilder = AlertDialog.Builder(this)
                .setTitle("MAD Lab")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK") { _, _ ->
                    Toast.makeText(this, "You clicked OK", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }

            val alert = dialogBuilder.create()
            alert.show()
        }
    }
}
