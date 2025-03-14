package com.example.fooddelivery.network

data class FiltersAndRestaurants(
    val restaurants: List<RestaurantsData>,
    val filters: List<FilterResponse>
)
