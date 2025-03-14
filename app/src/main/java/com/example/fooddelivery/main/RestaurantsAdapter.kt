package com.example.fooddelivery.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.databinding.CardItemBinding
import com.example.fooddelivery.network.RestaurantsData

class RestaurantsAdapter(private val onClick: (RestaurantsData) -> Unit) :
    RecyclerView.Adapter<RestaurantsViewHolder>() {

    private var listRestaurants = emptyList<RestaurantsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemBinding.inflate(inflater, parent,false)
        val viewHolder = RestaurantsViewHolder(binding, onClick)
        return viewHolder
    }

    override fun getItemCount(): Int {
       return listRestaurants.size
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val item = listRestaurants[position]
        holder.bind(item)
    }

    fun updateListRestaurants (newList: List<RestaurantsData>){
        listRestaurants = newList
        notifyDataSetChanged()
    }


}