package com.example.exp7_119

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private val STORAGE_PERMISSION_CODE = 101

    private lateinit var regEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var cgpaEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        regEditText = findViewById(R.id.registerNumberEditText)
        nameEditText = findViewById(R.id.nameEditText)
        cgpaEditText = findViewById(R.id.cgpaEditText)
        saveButton = findViewById(R.id.saveButton)
        loadButton = findViewById(R.id.loadButton)
        textView = findViewById(R.id.textViewOutput)

        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE)

        saveButton.setOnClickListener {
            val reg = regEditText.text.toString().trim()
            val name = nameEditText.text.toString().trim()
            val cgpa = cgpaEditText.text.toString().trim()

            if (reg.isEmpty() || name.isEmpty() || cgpa.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                writeFileOnExternalStorage(reg, name, cgpa)
            }
        }

        loadButton.setOnClickListener {
            val folder = File(getExternalFilesDir(null), "StudentData")
            val file = File(folder, "StudentDetails.txt")
            if (file.exists()) {
                val content = file.readText()
                textView.text = content
            } else {
                Toast.makeText(this, "No file found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun writeFileOnExternalStorage(registerNumber: String, name: String, cgpa: String) {
        val folder = File(getExternalFilesDir(null), "StudentData")
        if (!folder.exists()) {
            folder.mkdirs()
        }
        val file = File(folder, "StudentDetails.txt")
        FileOutputStream(file, true).bufferedWriter().use { writer ->
            writer.appendLine("Register Number: $registerNumber, Name: $name, CGPA: $cgpa")
        }
        Toast.makeText(this, "Saved to internal external storage!", Toast.LENGTH_SHORT).show()
    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
