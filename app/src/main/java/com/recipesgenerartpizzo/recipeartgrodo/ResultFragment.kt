package com.recipesgenerartpizzo.recipeartgrodo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.holidayscountrypackseven.holidaycanadaart.recycler.RecipeListAdapter
import com.recipesgenerartpizzo.recipeartgrodo.databinding.FragmentResultBinding
import com.recipesgenerartpizzo.recipeartgrodo.entity.RecipesListNetItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ResultFragment : Fragment() {

    private var list = mutableListOf<RecipesListNetItem>()

    private val adapter by lazy {
        RecipeListAdapter()
    }

    private val mainVievModel by activityViewModels<FavoriteRecipesVievmodel>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(RecipeService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    val api: RecipeService by lazy {
        retrofit.create(RecipeService::class.java)
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
        Log.d("favorit", "ResultFragment mainVievModel ${mainVievModel}")
        try {
            loadList()
            addVertAndHorDividers()
            initProgBar()
            initOnItemClickListener()
            initClickListenerAddFavorite()
        } catch (e: Exception) {
            snackBarError()
        }

        binding.btnGoToFavorites.setOnClickListener {
            try {
                findNavController().navigate(R.id.action_resultFragment_to_favoriteFoodRecipesFragment)
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
                val result = api.getRecipes()
                if (result.isSuccessful) {
                    list = result.body()!!
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
            binding.btnGoToFavorites.visibility = View.GONE
            binding.tvHelperName.visibility = View.GONE
            binding.tvHelperIngidients.visibility = View.GONE
            binding.cardV.visibility = View.GONE
            binding.btnImgExit.visibility = View.GONE
            delay(3000)
            binding.lottieAnimVaiting.visibility = View.VISIBLE
            binding.tvPleaseVaitLoading.visibility = View.VISIBLE

            binding.imgMain.visibility = View.VISIBLE
            binding.cardV.visibility = View.VISIBLE
            binding.tvHelperName.visibility = View.VISIBLE
            binding.tvHelperIngidients.visibility = View.VISIBLE
            binding.btnGoToFavorites.visibility = View.VISIBLE
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

    private fun initOnItemClickListener() {
        adapter.setOnItemClickListener {
            ResultFragmentDirections.actionResultFragmentToDetailFragment(recipe = it).also {
                findNavController().navigate(it)
            }
        }
    }

    private fun initClickListenerAddFavorite() {
        adapter.setOnItemClickListenerHeart {
            mainVievModel.addToShopCart(it)
        }
    }

}