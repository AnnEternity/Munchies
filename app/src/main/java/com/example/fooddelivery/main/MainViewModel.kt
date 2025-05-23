package com.example.fooddelivery.main

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.network.FilterResponse
import com.example.fooddelivery.network.RestaurantsData
import com.example.fooddelivery.repository.FoodDeliveryRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val foodDeliveryRepository = FoodDeliveryRepository()

    private val _restaurants = MutableLiveData<List<RestaurantsData>>()

    private val _filters = MutableLiveData<List<FilterResponse>>()
    val filters: LiveData<List<FilterResponse>>
        get() = _filters

    private val _selectedFilter = MutableLiveData<List<FilterResponse>>()
    val selectedFilter: LiveData<List<FilterResponse>>
        get() = _selectedFilter

    private val _filteredRestaurants = MutableLiveData<List<RestaurantsData>>()
    val filteredRestaurants: LiveData<List<RestaurantsData>>
        get() = _filteredRestaurants

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                val filtersAndRestaurants = foodDeliveryRepository.getFiltersAndRestaurants()
                _restaurants.value = filtersAndRestaurants.restaurants
                _filteredRestaurants.value = _restaurants.value
                _filters.value = filtersAndRestaurants.filters
            } catch (e: Exception) {
                Toast.makeText(getApplication(), "Network error: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun getRestaurantsBySelectedFilter(filter: FilterResponse) {
        val currentFilters = _selectedFilter.value ?: emptyList()

        val newList: List<FilterResponse> = if (currentFilters.contains(filter)) {
            currentFilters - filter
        } else {
            currentFilters + filter
        }
        _selectedFilter.value = newList

        _filteredRestaurants.value = if (newList.isEmpty()) {
            _restaurants.value
        } else {
            _restaurants.value?.filter { restaurant ->
                newList.all { filter ->
                    restaurant.filters.contains(filter)
                }
            }
        }
    }
}