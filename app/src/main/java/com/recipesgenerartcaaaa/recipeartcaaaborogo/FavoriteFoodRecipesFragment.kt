package com.recipesgenerartcaaaa.recipeartcaaaborogo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.holidayscountrypackseven.holidaycanadaart.recycler.RecipeListAdapter
import com.recipesgenerartcaaaa.recipeartcaaaborogo.databinding.FragmentFavoriteFoodRecipesBinding
import com.recipesgenerartcaaaa.recipeartcaaaborogo.databinding.FragmentResultBinding
import kotlinx.coroutines.launch

class FavoriteFoodRecipesFragment : Fragment() {

    private var _binding: FragmentFavoriteFoodRecipesBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    private val mainVievModel by activityViewModels<FavoriteRecipesVievmodel>()

    private val adapter by lazy {
        RecipeListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteFoodRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("favorit", "FavoriteFoodRecipesFragment mainVievModel ${mainVievModel}")
        try {
            binding.recyclerView.adapter = adapter
            addVertAndHorDividers()
            mainVievModel.listFavoriteRecipes.observe(viewLifecycleOwner){
                adapter.submitList(it)
            }
            binding.btnImgExit.setOnClickListener {
                initAlertDialog()
            }
        } catch (e: Exception){
            snackBarError()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun snackBarError() {
        Snackbar.make(
            binding.root,
            "There is some error, try again",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

    private fun saveToClipBoard() {
        Snackbar.make(binding.root, "Saved!", Snackbar.LENGTH_LONG).show()
    }

    private fun addVertAndHorDividers() {
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun initAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit")
            .setMessage("Are you definitely want to log out, the current data will not be saved?")
            .setPositiveButton("Yes, Exit") { _, _ ->
                requireActivity().onBackPressed()
            }
            .setNegativeButton("Deny") { _, _ ->
            }
            .setCancelable(true)
            .create()
            .show()
    }


}