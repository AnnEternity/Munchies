package com.example.fooddelivery.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantsData(
    val id: String?,
    val name: String?,
    val rating: Float?,
    val filters: List<FilterResponse>,
    val imageUrl: String?,
    val deliveryTimeMinutes: Int?
) : Parcelable {

    fun getFiltersString(): String {
        val filters = this.filters
        val filtersName = mutableListOf<String>()
        for (filter in filters) {
            filtersName.add(filter.name ?: continue)
        }
        val filtersText = filtersName.joinToString(separator = " • ")
        return filtersText
    }
}

