package com.example.android.recipe.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.recipe.R
import com.example.android.recipe.model.Recipe

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val recipeTitleTextView: TextView = itemView.findViewById(R.id.recipeTitleTextView)

    fun bind(recipe: Recipe) {
        recipeTitleTextView.text = recipe.title
    }
}
