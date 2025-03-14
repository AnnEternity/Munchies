package com.example.fooddelivery.main

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.FilterItemBinding
import com.example.fooddelivery.network.FilterResponse


class FiltersViewHolder(
    private val binding: FilterItemBinding,
    private val onClick: (FilterResponse) -> Unit
) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(item: FilterResponse, isSelected: Boolean) {
        binding.filterCard = item
        binding.filterImageView.load(item.url)
        binding.root.setOnClickListener {
            onClick(item)
        }
        if (isSelected){
            binding.cardViewFilter.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.Selected))
            binding.filterTextView.setTextColor(Color.WHITE)
        }else{
            binding.cardViewFilter.setCardBackgroundColor(Color.WHITE)
            binding.filterTextView.setTextColor(Color.BLACK)
        }
    }
}