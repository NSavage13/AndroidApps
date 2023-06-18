package com.example.android.tdd

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Activity3 : AppCompatActivity() {
    private lateinit var clickedTextView: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity3)

        clickedTextView = findViewById(R.id.clickedTextView)

        val clickedItem = intent.getStringExtra("ITEM_EXTRA")
        clickedTextView.text = "You clicked $clickedItem"
    }
}
