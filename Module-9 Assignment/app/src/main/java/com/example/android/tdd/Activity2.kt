package com.example.android.tdd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Activity2 : AppCompatActivity() {
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity2)

        itemRecyclerView = findViewById(R.id.itemRecyclerView)
        itemRecyclerView.layoutManager = LinearLayoutManager(this)

        val number = intent.getIntExtra("NUMBER_EXTRA", 0)
        val itemList = generateItemList(number)

        itemAdapter = ItemAdapter(itemList) { clickedItem ->
            val intent = Intent(this, Activity3::class.java)
            intent.putExtra("ITEM_EXTRA", clickedItem)
            startActivity(intent)
        }
        itemRecyclerView.adapter = itemAdapter
    }

    private fun generateItemList(number: Int): List<String> {
        val itemList = mutableListOf<String>()
        for (i in 1..number) {
            itemList.add("Item $i")
        }
        return itemList
    }
}
