package com.example.android.recipe

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.recipe.adapter.RecipeAdapter
import com.example.android.recipe.model.Flavor
import com.example.android.recipe.model.Recipe

class MainActivity : AppCompatActivity() {
    private lateinit var recipeAdapter: RecipeAdapter
    private val recipes: MutableList<Recipe> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recipeAdapter = RecipeAdapter()
        val recipeRecyclerView: RecyclerView = findViewById(R.id.recipeRecyclerView)
        recipeRecyclerView.layoutManager = LinearLayoutManager(this)
        recipeRecyclerView.adapter = recipeAdapter

        // Add swipe functionality to remove recipes
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                recipes.removeAt(position)
                recipeAdapter.setRecipes(recipes)
            }
        })
        itemTouchHelper.attachToRecyclerView(recipeRecyclerView)
    }

    fun addSweetRecipe(view: View) {
        val recipeTitleEditText: EditText = findViewById(R.id.main_recipe_title)
        val recipeDescriptionEditText: EditText = findViewById(R.id.main_recipe_description)
        val title = recipeTitleEditText.text.toString()
        val description = recipeDescriptionEditText.text.toString()

        val recipe = Recipe(title, description, Flavor.SWEET)
        recipes.add(recipe)
        recipeAdapter.setRecipes(recipes)

        recipeTitleEditText.text.clear()
        recipeDescriptionEditText.text.clear()
    }

    fun addSavoryRecipe(view: View) {
        val recipeTitleEditText: EditText = findViewById(R.id.main_recipe_title)
        val recipeDescriptionEditText: EditText = findViewById(R.id.main_recipe_description)
        val title = recipeTitleEditText.text.toString()
        val description = recipeDescriptionEditText.text.toString()

        val recipe = Recipe(title, description, Flavor.SAVORY)
        recipes.add(recipe)
        recipeAdapter.setRecipes(recipes)

        recipeTitleEditText.text.clear()
        recipeDescriptionEditText.text.clear()
    }

    fun showRecipeDescription(recipe: Recipe) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(recipe.title)
        dialogBuilder.setMessage(recipe.description)
        dialogBuilder.setPositiveButton("OK", null)
        dialogBuilder.create().show()
    }
}

