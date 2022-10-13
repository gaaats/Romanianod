package com.recipesgenerartpizzo.recipeartgrodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import com.recipesgenerartpizzo.recipeartgrodo.databinding.FragmentRandomRecipeBinding
import kotlin.random.Random

class RandomRecipeFragment : Fragment() {

    private var _binding: FragmentRandomRecipeBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    private val sectors = arrayOf(
        "Cheese",
        "Pineapple",
        "Meat",
        "Seafood",
        "Margarita",
        "Diablo",
        "Carbonara",
    )
    private val sectorDegrees = arrayOf(1,1,1,1,1,1,1)
    private val singleSectorDegree = 360 / sectors.size
    private var isSpinning = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnImgExit.setOnClickListener {
            requireActivity().onBackPressed()
        }

        getDegreeForSectors()
        binding.btnGameFortuneSpin.setOnClickListener {
            if (!isSpinning) {
                spin()
                isSpinning = true
            }
        }




        super.onViewCreated(view, savedInstanceState)
    }

    private fun spin() {
        val winnerNumber = Random.nextInt(sectors.size - 1)

        //calculate number of degrees for set pointer to correct sector in UI
        val needAddRotate = (360 - winnerNumber * singleSectorDegree).toFloat()
        val rotateAnimation = RotateAnimation(
            0f,
            (360f * sectors.size) + needAddRotate,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f
        )
        rotateAnimation.apply {
            duration = 1000
            fillAfter = true
            interpolator = DecelerateInterpolator()
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    val userResult = sectors[winnerNumber]
                    Toast.makeText(
                        requireContext(),
                        "Try to cook $userResult",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    isSpinning = false
                }
                override fun onAnimationRepeat(p0: Animation?) {
                }
            })
            binding.imgWheelElementMain.startAnimation(rotateAnimation)
        }
    }

    private fun getDegreeForSectors() {
        for (i in sectors.indices) {
            sectorDegrees[i] = (i + 1) * singleSectorDegree
        }
    }


}