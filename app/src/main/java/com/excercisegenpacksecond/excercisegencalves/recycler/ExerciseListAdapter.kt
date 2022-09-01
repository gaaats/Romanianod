package com.excercisegenpacksecond.excercisegencalves.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.excercisegenpacksecond.excercisegencalves.R
import com.excercisegenpacksecond.excercisegencalves.databinding.SingleExerciseBinding


class ExerciseListAdapter() :
    ListAdapter<ExerciseItem, ExerciseListAdapter.ExerciseVievHolder>(ExerciseDiffUtill()) {

    class ExerciseVievHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SingleExerciseBinding.bind(itemView)
    }

    // todo: make it later
    interface OnItemInsideShopCartNavigation {
    }

    private var onItemPlusClickListener: ((geekProductUI: ExerciseItem) -> Unit)? = null
    private var onItemMinusClickListener: ((geekProductUI: ExerciseItem) -> Unit)? = null
    private var onItemDeleteClickListener: ((geekProductUI: ExerciseItem, countProductPreDelete: Int) -> Unit)? =
        null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseVievHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.single_exercise, parent, false).also {
                return ExerciseVievHolder(it)
            }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExerciseVievHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            tvExName.text = currentItem.name
            tvExEquipment.text = currentItem.equipment
        }
    }
}