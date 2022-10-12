package com.holidayscountrypackseven.holidaycanadaart.recycler

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.recipesgenerartcaaaa.recipeartcaaaborogo.R
import com.recipesgenerartcaaaa.recipeartcaaaborogo.databinding.SingleFoodBinding
import com.recipesgenerartcaaaa.recipeartcaaaborogo.entity.RecipesListNetItem


class RecipeListAdapter() :
    ListAdapter<RecipesListNetItem, RecipeListAdapter.HolidaysVievHolder>(RecipesDiffUtill()) {

    private var onItemClickListener: ((holiday: RecipesListNetItem) -> Unit)? = null
    private var addToFavorite: ((recipe: RecipesListNetItem) -> Unit)? = null

    class HolidaysVievHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SingleFoodBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidaysVievHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.single_food, parent, false).also {
                return HolidaysVievHolder(it)
            }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HolidaysVievHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            tvName.text = currentItem._title
            tvIngridients.text = currentItem._ingredients
            root.setOnClickListener {
                onItemClickListener?.invoke(currentItem)
            }
            imgAddToFavorite.setOnClickListener {
                addToFavorite?.invoke(currentItem)
                Log.d("favorit", "pressed ${currentItem._title}")
                Snackbar.make(
                    this.root,
                    "Added to favorite ♥♥♥",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    fun setOnItemClickListener(listener: (holidayName: RecipesListNetItem) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnItemClickListenerHeart(listener: (holidayName: RecipesListNetItem) -> Unit) {
        addToFavorite = listener
    }
}