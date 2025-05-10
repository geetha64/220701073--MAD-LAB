package com.example.exp13_119

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import android.util.Log


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var textToSpeech: TextToSpeech
    private lateinit var editText: EditText
    private lateinit var speakButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        editText = findViewById(R.id.editText)
        speakButton = findViewById(R.id.speakButton)

        // Initialize TextToSpeech
        textToSpeech = TextToSpeech(this, this)

        // Speak button click listener
        speakButton.setOnClickListener {
            val text = editText.text.toString().trim()
            if (text.isNotEmpty()) {
                speakText(text)
            } else {
                Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onInit(status: Int) {
        Log.d("TTS_STATUS", "Status: $status") // Log the init status

        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Language not supported or missing data", Toast.LENGTH_LONG).show()

                // Prompt to install missing TTS data
                val installIntent = Intent()
                installIntent.action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA
                startActivity(installIntent)
            }
        } else {
            Toast.makeText(this, "TextToSpeech Initialization Failed", Toast.LENGTH_SHORT).show()
        }
    }



    private fun speakText(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        // Shutdown TTS to free resources
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}
