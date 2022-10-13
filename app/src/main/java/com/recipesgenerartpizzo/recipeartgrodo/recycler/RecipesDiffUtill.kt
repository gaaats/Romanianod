package com.holidayscountrypackseven.holidaycanadaart.recycler
import androidx.recyclerview.widget.DiffUtil
import com.recipesgenerartpizzo.recipeartgrodo.entity.RecipesListNetItem

class RecipesDiffUtill: DiffUtil.ItemCallback<RecipesListNetItem>() {
    override fun areItemsTheSame(oldItem: RecipesListNetItem, newItem: RecipesListNetItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: RecipesListNetItem, newItem: RecipesListNetItem): Boolean {
        return oldItem == newItem
    }
}