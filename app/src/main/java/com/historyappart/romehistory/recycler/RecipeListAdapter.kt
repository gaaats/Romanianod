package com.historyappart.romehistory.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.historyappart.romehistory.R
import com.historyappart.romehistory.databinding.SingleEventBinding
import com.historyappart.romehistory.historyevents.EventItem


class RecipeListAdapter() :
    ListAdapter<EventItem, RecipeListAdapter.EventsVievHolder>(RecipesDiffUtill()) {

    private var onItemClickListener: ((event: EventItem) -> Unit)? = null
    private var addToFavorite: ((event: EventItem) -> Unit)? = null

    class EventsVievHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SingleEventBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsVievHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.single_event, parent, false).also {
                return EventsVievHolder(it)
            }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EventsVievHolder, position: Int) {

        val listImages = listOf(
            R.drawable.e1,
            R.drawable.e2,
            R.drawable.e3,
            R.drawable.e4,
            R.drawable.e5,
            R.drawable.e6,
            R.drawable.e7,
            R.drawable.e8,
            R.drawable.e9,

        )

        val currentItem = getItem(position)
        holder.binding.apply {
            tvEvent.text = currentItem.event
            tvYear.text = currentItem.year
            imgPicture.setImageResource(listImages.random())
            root.setOnClickListener {
//                onItemClickListener?.invoke(currentItem)
                Snackbar.make(
                    this.root,
                    "The day is: ${currentItem.day}, month is ${currentItem.month}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
//            imgAddToFavorite.setOnClickListener {
//                addToFavorite?.invoke(currentItem)
//                Log.d("favorit", "pressed ${currentItem._title}")
//                Snackbar.make(
//                    this.root,
//                    "Added to favorite ♥♥♥",
//                    Snackbar.LENGTH_LONG
//                ).show()
//            }
        }
    }

//    fun setOnItemClickListener(listener: (holidayName: RecipesListNetItem) -> Unit) {
//        onItemClickListener = listener
//    }
//
//    fun setOnItemClickListenerHeart(listener: (holidayName: RecipesListNetItem) -> Unit) {
//        addToFavorite = listener
//    }
}