package com.example.fooddelivery.repository

import com.example.fooddelivery.network.FilterResponse
import com.example.fooddelivery.network.FiltersAndRestaurants
import com.example.fooddelivery.network.FoodDeliveryApi
import com.example.fooddelivery.network.RestaurantResponse
import com.example.fooddelivery.network.RestaurantsData

class FoodDeliveryRepository {

    private suspend fun getFilter(id: String): FilterResponse {
        return FoodDeliveryApi.retrofitService.getFilter(filterId = id)
    }

    suspend fun getOpenStatus(id: String): Boolean? {
        val status = FoodDeliveryApi.retrofitService.getOpenStatus(restaurantId = id)
        return status.isCurrentlyOpen
    }

    suspend fun getFiltersAndRestaurants(): FiltersAndRestaurants {
        val restaurantsResponse = FoodDeliveryApi.retrofitService.getAllRestaurants()
        val filterIdsList = mutableListOf<String>()

        for (restaurant in restaurantsResponse.restaurants) {
            filterIdsList.addAll(restaurant.filterIds ?: emptyList())
        }
        val filterIdsSet = filterIdsList.toSet()
        val filtersMap = mutableMapOf<String, FilterResponse>()
        for (filterId in filterIdsSet) {
            val filter = getFilter(filterId)
            filtersMap[filterId] = filter
        }

        val restaurantsData = getRestaurantsData(restaurantsResponse, filtersMap)
        val filtersAndRestaurants =
            FiltersAndRestaurants(restaurantsData, filtersMap.values.toList())
        return filtersAndRestaurants
    }

    private fun getRestaurantsData(
        restaurantsResponse: RestaurantResponse,
        filtersMap: MutableMap<String, FilterResponse>
    ): MutableList<RestaurantsData> {
        val restaurantsData = mutableListOf<RestaurantsData>()
        for (restaurant in restaurantsResponse.restaurants) {
            val restaurantsFilters = mutableListOf<FilterResponse>()
            val filtersIdsForOneRestaurant = restaurant.filterIds ?: emptyList()
            for (filter in filtersIdsForOneRestaurant) {
                val filterObj = filtersMap[filter] ?: continue
                restaurantsFilters.add(filterObj)
            }
            val newRestaurantData = RestaurantsData(
                id = restaurant.id,
                name = restaurant.name,
                rating = restaurant.rating,
                filters = restaurantsFilters,
                imageUrl = restaurant.imageUrl,
                deliveryTimeMinutes = restaurant.deliveryTimeMinutes
            )
            restaurantsData.add(newRestaurantData)
        }
        return restaurantsData
    }
}