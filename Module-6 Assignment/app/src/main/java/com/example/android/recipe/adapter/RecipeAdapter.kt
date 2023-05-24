package com.example.android.recipe.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.recipe.MainActivity
import com.example.android.recipe.R
import com.example.android.recipe.model.Flavor
import com.example.android.recipe.model.Recipe
import com.example.android.recipe.viewholder.FlavorTitleViewHolder

class RecipeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: MutableList<Any> = mutableListOf()

    fun setRecipes(recipes: List<Recipe>) {
        items.clear()
        items.addAll(getGroupedItems(recipes))
        notifyDataSetChanged()
    }

    private fun getGroupedItems(recipes: List<Recipe>): List<Any> {
        val groupedItems: MutableList<Any> = mutableListOf()

        groupedItems.add(Flavor.SWEET)
        recipes.filter { it.flavor == Flavor.SWEET }.forEach { groupedItems.add(it) }

        groupedItems.add(Flavor.SAVORY)
        recipes.filter { it.flavor == Flavor.SAVORY }.forEach { groupedItems.add(it) }

        return groupedItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_FLAVOR_TITLE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_flavor_title, parent, false)
                FlavorTitleViewHolder(view)
            }
            VIEW_TYPE_RECIPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_recipe, parent, false)
                RecipeViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is FlavorTitleViewHolder -> {
                val flavor = item as Flavor
                holder.bind(flavor)
            }
            is RecipeViewHolder -> {
                val recipe = item as Recipe
                holder.bind(recipe)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Flavor -> VIEW_TYPE_FLAVOR_TITLE
            is Recipe -> VIEW_TYPE_RECIPE
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    override fun getItemCount(): Int = items.size

    companion object {
        private const val VIEW_TYPE_FLAVOR_TITLE = 0
        private const val VIEW_TYPE_RECIPE = 1
    }
    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeTitleTextView: TextView = itemView.findViewById(R.id.recipeTitleTextView)

        init {
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val recipe = items[position]
                    val activity = itemView.context as MainActivity
                    activity.showRecipeDescription(recipe as Recipe)
                }
            }
        }

        fun bind(recipe: Recipe) {
            recipeTitleTextView.text = recipe.title
        }
    }
}
