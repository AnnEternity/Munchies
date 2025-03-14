package com.example.fooddelivery.main

import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.fooddelivery.databinding.CardItemBinding
import com.example.fooddelivery.network.RestaurantsData

class RestaurantsViewHolder(
    private val cardItem: CardItemBinding,
    private val onClick: (RestaurantsData) -> Unit
) :
    RecyclerView.ViewHolder(cardItem.root) {
    fun bind(restaurantItem: RestaurantsData) {
        cardItem.restaurantCard = restaurantItem
        cardItem.restaurantCardImageView.load(restaurantItem.imageUrl)

        cardItem.filtersTextView.text = restaurantItem.getFiltersString()

        cardItem.root.setOnClickListener {
            onClick(restaurantItem)
        }
    }
}