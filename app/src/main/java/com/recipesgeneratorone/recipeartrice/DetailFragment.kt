package com.recipesgeneratorone.recipeartrice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.recipesgeneratorone.recipeartrice.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recipe = args.recipe
        binding.tvTitleRecipe.text = recipe._title
        binding.tvRecipeIngridients.text = recipe._ingredients
        binding.tvRecipeInstructions.text = recipe._instructions
        initBtns()
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initBtns() {
        binding.btnImgExit.setOnClickListener {
            goBack()
        }
        binding.btnOk.setOnClickListener {
            goBack()
        }
    }

    private fun goBack() {
        requireActivity().onBackPressed()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}