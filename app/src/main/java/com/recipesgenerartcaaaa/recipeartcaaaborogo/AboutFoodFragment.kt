package com.recipesgenerartcaaaa.recipeartcaaaborogo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.recipesgenerartcaaaa.recipeartcaaaborogo.databinding.FragmentAboutFoodBinding
import com.recipesgenerartcaaaa.recipeartcaaaborogo.databinding.FragmentDetailBinding


class AboutFoodFragment : Fragment() {

    private var _binding: FragmentAboutFoodBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnImgExit.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.btnHistory.setOnClickListener {
            findNavController().navigate(R.id.action_aboutFoodFragment_to_historyFragment)
        }
        binding.btnDisadvantages.setOnClickListener {
            findNavController().navigate(R.id.action_aboutFoodFragment_to_disadvantagesFragment)
        }
        binding.btnPhotos.setOnClickListener {
            findNavController().navigate(R.id.action_aboutFoodFragment_to_flavorFragment)
        }
        binding.btnCultivation.setOnClickListener {
            findNavController().navigate(R.id.action_aboutFoodFragment_to_cultivationFragment)
        }
        binding.btnCountyFrom.setOnClickListener {
            findNavController().navigate(R.id.action_aboutFoodFragment_to_countryFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }

}