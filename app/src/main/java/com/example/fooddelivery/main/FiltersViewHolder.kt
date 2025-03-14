package com.example.fooddelivery.main

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.FilterItemBinding
import com.example.fooddelivery.network.FilterResponse


class FiltersViewHolder(
    private val filterItem: FilterItemBinding,
    private val onClick: (FilterResponse) -> Unit
) : RecyclerView.ViewHolder(filterItem.root)
{
    fun bind(item: FilterResponse, isSelected: Boolean) {
        filterItem.filterCard = item
        filterItem.filterImageView.load(item.url)
        filterItem.root.setOnClickListener {
            onClick(item)
        }
        if (isSelected){
            filterItem.cardViewFilter.setCardBackgroundColor(ContextCompat.getColor(filterItem.root.context, R.color.Selected))
            filterItem.filterTextView.setTextColor(Color.WHITE)
        }else{
            filterItem.cardViewFilter.setCardBackgroundColor(Color.WHITE)
            filterItem.filterTextView.setTextColor(Color.BLACK)
        }
    }
}