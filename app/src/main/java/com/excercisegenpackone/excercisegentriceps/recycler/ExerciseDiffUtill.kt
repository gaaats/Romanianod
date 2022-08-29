package com.excercisegenpackone.excercisegentriceps.recycler
import androidx.recyclerview.widget.DiffUtil

class ExerciseDiffUtill: DiffUtil.ItemCallback<ExerciseItem>() {
    override fun areItemsTheSame(oldItem: ExerciseItem, newItem: ExerciseItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExerciseItem, newItem: ExerciseItem): Boolean {
        return oldItem == newItem
    }
}