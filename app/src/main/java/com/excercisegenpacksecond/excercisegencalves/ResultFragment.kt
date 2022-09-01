package com.excercisegenpacksecond.excercisegencalves

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.excercisegenpacksecond.excercisegencalves.databinding.FragmentResultBinding
import com.excercisegenpacksecond.excercisegencalves.recycler.ExerciseItem
import com.excercisegenpacksecond.excercisegencalves.recycler.ExerciseListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ResultFragment : Fragment() {

    private var list = mutableListOf<ExerciseItem>()

    private val adapter by lazy {
        ExerciseListAdapter()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(MuscleService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    val api: MuscleService by lazy {
        retrofit.create(MuscleService::class.java)
    }

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        try {
            loadList()
            addVertAndHorDividers()
            initProgBar()
        } catch (e: Exception) {
            snackBarError()
        }


        binding.btnCopy.setOnClickListener {
            try {
                saveToClipBoard()
            } catch (e: Exception) {
                snackBarError()
            }
        }
        binding.btnImgExit.setOnClickListener {
            initAlertDialog()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadList() {
        lifecycleScope.launch {
            try {
                val result = api.getExercises()
                if (result.isSuccessful) {
                    Log.d("test_tag", "good")

                    result.body()?.forEach {
                        Log.d("test_tag", "name ${it.name}")
                        Log.d("test_tag", "name ${it.equipment}")
                    }

                    list = result.body()!!.map {
                        it.mapToUiModel()
                    }.toMutableList()
                    adapter.submitList(list)
                    binding.recyclerView.adapter = adapter


                } else {
                    snackBarError()
                }
            } catch (e: Exception) {
                snackBarError()
            }
        }
    }

    private fun snackBarError() {
        Snackbar.make(
            binding.root,
            "There is some error, try again",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

    private fun initProgBar() {
        lifecycleScope.launch {
            binding.imgMain.visibility = View.GONE
            binding.btnCopy.visibility = View.GONE
            binding.recyclerView.visibility = View.GONE
            binding.btnImgExit.visibility = View.GONE
            delay(2000)
            binding.lottieAnimVaiting.visibility = View.VISIBLE
            binding.tvPleaseVaitLoading.visibility = View.VISIBLE

            binding.imgMain.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.VISIBLE
            binding.btnCopy.visibility = View.VISIBLE
            binding.btnImgExit.visibility = View.VISIBLE
            binding.lottieAnimVaiting.visibility = View.GONE
            binding.tvPleaseVaitLoading.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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