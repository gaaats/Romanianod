package com.recipesgenerartcaaaa.recipeartcaaaborogo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recipesgenerartcaaaa.recipeartcaaaborogo.entity.RecipesListNetItem

class FavoriteRecipesVievmodel: ViewModel() {

    private var _listFavoriteRecipes = MutableLiveData<MutableList<RecipesListNetItem>>()
    val listFavoriteRecipes: LiveData<MutableList<RecipesListNetItem>>
        get() = _listFavoriteRecipes

    private var _isListEmpty = MutableLiveData<Boolean>()
    val isListEmpty: LiveData<Boolean>
        get() = _isListEmpty


    init {

        _listFavoriteRecipes.value = mutableListOf()
    }




    fun addToShopCart(singleRecipe: RecipesListNetItem) {
        Log.d("favorit", "in vievmodel pressed add")
        if (checkIsItemAlreadyInShopCart(singleRecipe)) {
            return
        }
        val savedList = mutableListOf<RecipesListNetItem>()
        _listFavoriteRecipes.value?.forEach {
            savedList.add(it)
        }
        savedList.add(singleRecipe)
        _listFavoriteRecipes.value = savedList
        makeShopCartStateIsFull()
    }



    private fun makeShopCartStateIsFull() {
        _isListEmpty.value = false
    }

    private fun checkIsItemAlreadyInShopCart(singleRecipe: RecipesListNetItem): Boolean {
        _listFavoriteRecipes.value!!.forEach {
            if (it._title == singleRecipe._title) return true
        }
        return false
    }


}