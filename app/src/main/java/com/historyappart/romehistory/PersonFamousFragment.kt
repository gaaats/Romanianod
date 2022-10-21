package com.historyappart.romehistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.historyappart.romehistory.databinding.FragmentCountryBinding
import com.historyappart.romehistory.databinding.FragmentPersonFamousBinding
import com.historyappart.romehistory.famouspeople.FamousPeopleService
import com.historyappart.romehistory.famouspeople.MyInterceptor
import com.historyappart.romehistory.historyevents.HistoryEventsService
import com.historyappart.romehistory.recycler.RecipeListAdapter
import com.historyappart.romehistory.utils.VievPagerAdapter
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonFamousFragment : Fragment() {
    private var _binding: FragmentPersonFamousBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    val listImages = listOf(
        R.drawable.person_1,
        R.drawable.person_2,
        R.drawable.person_3,
        R.drawable.person_4,
        R.drawable.person_5,
    )

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(FamousPeopleService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    val api: FamousPeopleService by lazy {
        retrofit.create(FamousPeopleService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonFamousBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = VievPagerAdapter(listImages)
        binding.pager.adapter = adapter
        binding.circleIndicator.setViewPager(binding.pager)

        loadList()

        binding.btnImgExit.setOnClickListener {
            initAlertDialog()
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

    private fun loadList() {
        lifecycleScope.launch {
            try {
//                val result = api.getEvents()
                binding.tvPersonName.text = "Julius Caesar"
                binding.tvPersonTitle.text = "Roman general and statesman"
                binding.tvPersonAvards.text = "Civic Crown"
                binding.tvPersonBorn.text = "12 July 100 BC Rome Italy"
                binding.tvPersonPartners.text = "Cleopatra"
                binding.tvPersonRestPlace.text = "Temple of Caesar Rome"

//                if (result.isSuccessful) {
//                    val famousPerson = result.body()!!.random()
//
////                    binding.tvPersonName.text = famousPerson.name
////                    binding.tvPersonTitle.text = famousPerson.title
////                    binding.tvPersonAvards.text = famousPerson.info!!.awards
////                    binding.tvPersonBorn.text = famousPerson.info!!.born
////                    binding.tvPersonPartners.text = famousPerson.info.partners
////                    binding.tvPersonRestPlace.text = famousPerson.info.restingPlace
//
//
//                } else {
//                    snackBarError()
//                }
            } catch (e: Exception) {
                snackBarError()
            }
        }
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