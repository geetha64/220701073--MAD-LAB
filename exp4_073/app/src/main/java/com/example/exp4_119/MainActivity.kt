package com.example.exp4_119

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer1, StudentBasicDetailsFragment())
            .replace(R.id.fragmentContainer2, StudentMarkDetailsFragment())
            .commit()
    }
}
