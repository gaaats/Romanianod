package com.historyappart.romehistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.historyappart.romehistory.databinding.FragmentEnterBinding


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
            binding.btnImgSettings.setOnClickListener {
                findNavController().navigate(R.id.action_enterFragment_to_settingsFragment)
            }
            binding.imgAboutCountry.setOnClickListener {
                findNavController().navigate(R.id.action_enterFragment_to_countryFragment)
            }
            binding.imgFamousPerson.setOnClickListener {
                findNavController().navigate(R.id.action_enterFragment_to_personFamousFragment)
            }
            binding.imgHistoricalEvents.setOnClickListener {
                findNavController().navigate(R.id.action_enterFragment_to_resultFragment)
            }
            binding.imgSoldiers.setOnClickListener {
                findNavController().navigate(R.id.action_enterFragment_to_soldiersFragment)
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