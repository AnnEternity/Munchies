package com.example.fooddelivery.detail

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.network.RestaurantsData
import com.example.fooddelivery.repository.FoodDeliveryRepository
import kotlinx.coroutines.launch

class DetailViewModel (application: Application, savedStateHandle: SavedStateHandle) : AndroidViewModel(application)  {

    private val foodDeliveryRepository = FoodDeliveryRepository()
    private val args = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val _isOpen = MutableLiveData<Boolean?>()
    val isOpen: LiveData<Boolean?>
        get() = _isOpen

    init {
        getIsOpen(args.restaurant)
    }

    private fun getIsOpen (restaurant: RestaurantsData){
        viewModelScope.launch {
           try {
               _isOpen.value = restaurant.id?.let { foodDeliveryRepository.getOpenStatus(it) }
           }
           catch (e:Exception){
               _isOpen.value = null
               Toast.makeText(getApplication(), "Network error: ${e.message}", Toast.LENGTH_SHORT).show()
           }
        }
    }



}