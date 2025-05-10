package com.example.exp10_119

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val operatorName = findViewById<EditText>(R.id.networkOperatorName)
        val phoneType = findViewById<EditText>(R.id.phoneType)
        val networkCountry = findViewById<EditText>(R.id.networkCountryIso)
        val simCountry = findViewById<EditText>(R.id.simCountryIso)
        val softwareVersion = findViewById<EditText>(R.id.deviceSoftwareVersion)
        val fetchBtn = findViewById<Button>(R.id.fetchBtn)
        val outputText = findViewById<TextView>(R.id.outputText)

        fetchBtn.setOnClickListener {
            val message = """
                Network Operator: ${operatorName.text}
                Phone Type: ${phoneType.text}
                Network Country ISO: ${networkCountry.text}
                SIM Country ISO: ${simCountry.text}
                Software Version: ${softwareVersion.text}
            """.trimIndent()

            outputText.text = message
        }
    }
}
