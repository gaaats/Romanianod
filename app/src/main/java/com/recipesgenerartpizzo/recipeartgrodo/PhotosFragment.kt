package com.recipesgenerartpizzo.recipeartgrodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.recipesgenerartpizzo.recipeartgrodo.databinding.FragmentPhotosBinding
import com.recipesgenerartpizzo.recipeartgrodo.utils.VievPagerAdapter

class PhotosFragment : Fragment() {
    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnImgExit.setOnClickListener {
            requireActivity().onBackPressed()
        }
        val listOfImages = createListOfImages()
        val adapter = VievPagerAdapter(listOfImages)
        binding.pager.adapter = adapter
        binding.circleIndicator.setViewPager(binding.pager)


        super.onViewCreated(view, savedInstanceState)
    }

    private fun createListOfImages(): List<Int> {
        return listOf(
            R.drawable.one,
            R.drawable.tvo,
            R.drawable.three,
            R.drawable.four,
        )
    }

}