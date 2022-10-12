package com.recipesgenerartcaaaa.recipeartcaaaborogo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.recipesgenerartcaaaa.recipeartcaaaborogo.databinding.FragmentCultivationBinding
import com.recipesgenerartcaaaa.recipeartcaaaborogo.databinding.FragmentDisadvantagesBinding

class DisadvantagesFragment : Fragment() {


    private var _binding: FragmentDisadvantagesBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDisadvantagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnImgExit.setOnClickListener {
            requireActivity().onBackPressed()
        }
        super.onViewCreated(view, savedInstanceState)
    }
}