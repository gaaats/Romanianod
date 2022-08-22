package com.passmathart.passgeneratorstarwars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.passmathart.passgeneratorstarwars.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnConfirm.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_resultFragment)
        }
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}