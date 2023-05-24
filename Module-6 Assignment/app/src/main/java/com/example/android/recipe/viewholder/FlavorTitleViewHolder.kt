package com.example.android.recipe.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.recipe.R
import com.example.android.recipe.model.Flavor

class FlavorTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val flavorTitleTextView: TextView = itemView.findViewById(R.id.flavorTitleTextView)

    fun bind(flavor: Flavor) {
        flavorTitleTextView.text = flavor.name
    }
}
