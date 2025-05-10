package com.example.exp5_119

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: UsersDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = UsersDBHelper(this)

        val regNo = findViewById<EditText>(R.id.etRegisterNumber)
        val name = findViewById<EditText>(R.id.etName)
        val cgpa = findViewById<EditText>(R.id.etCGPA)

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val user = UserModel.fromStrings(
                regNo.text.toString(),
                name.text.toString(),
                cgpa.text.toString()
            )
            if (dbHelper.insertStudent(user)) {
                showToast("Student Added")
                clearFields(regNo, name, cgpa)
            } else showToast("Failed to Add")
        }

        findViewById<Button>(R.id.btnView).setOnClickListener {
            val students = dbHelper.getAllStudents()
            if (students.isEmpty()) {
                showAlert("No Data", "No student records found.")
            } else {
                val msg = students.joinToString("\n") {
                    "RegNo: ${it.regNo}, Name: ${it.name}, CGPA: ${it.cgpa}"
                }
                showAlert("Student List", msg)
            }
        }

        findViewById<Button>(R.id.btnModify).setOnClickListener {
            val user = UserModel.fromStrings(
                regNo.text.toString(),
                name.text.toString(),
                cgpa.text.toString()
            )
            if (dbHelper.updateStudent(user)) {
                showToast("Student Updated")
                clearFields(regNo, name, cgpa)
            } else showToast("Update Failed")
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            val result = dbHelper.deleteStudent(regNo.text.toString())
            if (result) {
                showToast("Student Deleted")
                clearFields(regNo, name, cgpa)
            } else showToast("Delete Failed")
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            clearFields(regNo, name, cgpa)
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showAlert(title: String, msg: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(msg)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun clearFields(vararg fields: EditText) {
        fields.forEach { it.text.clear() }
    }
}
