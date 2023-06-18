package com.example.android.tdd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Activity1 : AppCompatActivity() {
    private lateinit var numberEditText: EditText
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity1)

        numberEditText = findViewById(R.id.numberEditText)
        nextButton = findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            val number = numberEditText.text.toString().toInt()
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("NUMBER_EXTRA", number)
            startActivity(intent)
        }
    }
}
