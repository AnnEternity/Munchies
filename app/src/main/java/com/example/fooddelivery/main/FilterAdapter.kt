package com.example.fooddelivery.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.databinding.FilterItemBinding
import com.example.fooddelivery.network.FilterResponse

class FilterAdapter(private val onClick: (FilterResponse) -> Unit) :
    RecyclerView.Adapter<FiltersViewHolder>() {

    private var listFilter = emptyList<FilterResponse>()
    private var selectedFilter = emptyList<FilterResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FilterItemBinding.inflate(inflater, parent, false)
        val viewHolder = FiltersViewHolder(binding, onClick)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return listFilter.size
    }

    override fun onBindViewHolder(holder: FiltersViewHolder, position: Int) {
        val item = listFilter[position]
        val isSelected = item in selectedFilter

        holder.bind(item, isSelected)
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    fun updateSelectedFilters(newList: List<FilterResponse>) {
        selectedFilter = newList
        notifyDataSetChanged()
    }

    fun updateListFilters(newList: List<FilterResponse>) {
        listFilter = newList
        notifyDataSetChanged()
    }


}