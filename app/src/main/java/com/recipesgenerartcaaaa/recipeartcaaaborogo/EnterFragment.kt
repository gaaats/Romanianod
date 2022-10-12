package com.recipesgenerartcaaaa.recipeartcaaaborogo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.recipesgenerartcaaaa.recipeartcaaaborogo.databinding.FragmentDetailBinding
import com.recipesgenerartcaaaa.recipeartcaaaborogo.databinding.FragmentEnterBinding


class EnterFragment : Fragment() {


    private var _binding: FragmentEnterBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.btnHeart.setOnClickListener {
                Snackbar.make(binding.root, "I love you too ♥♥♥", Snackbar.LENGTH_LONG).show()
            }
            binding.btnSettings.setOnClickListener {
                findNavController().navigate(R.id.action_enterFragment_to_settingsFragment)
            }
            binding.btnFavorites.setOnClickListener {
                findNavController().navigate(R.id.action_enterFragment_to_favoriteFoodRecipesFragment)
            }
            binding.btnInterestingFacts.setOnClickListener {
                findNavController().navigate(R.id.action_enterFragment_to_aboutFoodFragment)
            }
            binding.btnAllRecipes.setOnClickListener {
                findNavController().navigate(R.id.action_enterFragment_to_startFragment)
            }
        } catch (e: Exception) {
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
}