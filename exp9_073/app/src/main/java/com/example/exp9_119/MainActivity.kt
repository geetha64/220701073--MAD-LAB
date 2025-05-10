package com.example.exp9_119

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var timePicker: TimePicker
    private lateinit var setAlarmBtn: Button
    private lateinit var stopAlarmBtn: Button
    private lateinit var statusText: TextView
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timePicker = findViewById(R.id.timePicker)
        setAlarmBtn = findViewById(R.id.setAlarmBtn)
        stopAlarmBtn = findViewById(R.id.stopAlarmBtn)
        statusText = findViewById(R.id.statusText)

        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        setAlarmBtn.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
            calendar.set(Calendar.MINUTE, timePicker.minute)
            calendar.set(Calendar.SECOND, 0)

            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
            statusText.text = "Alarm ON...!"
            Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show()
        }

        stopAlarmBtn.setOnClickListener {
            alarmManager.cancel(pendingIntent)
            statusText.text = "Alarm OFF...!"
            Toast.makeText(this, "Alarm is cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}
