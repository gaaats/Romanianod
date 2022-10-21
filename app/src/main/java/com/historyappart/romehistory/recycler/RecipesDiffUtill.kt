package com.historyappart.romehistory.recycler
import androidx.recyclerview.widget.DiffUtil
import com.historyappart.romehistory.entity.RecipesListNetItem
import com.historyappart.romehistory.historyevents.EventItem

class RecipesDiffUtill: DiffUtil.ItemCallback<EventItem>() {
    override fun areItemsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
        return oldItem.event == newItem.event
    }

    override fun areContentsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
        return oldItem == newItem
    }
}