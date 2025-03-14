package com.example.fooddelivery.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelivery.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    private val adapterRestaurant = RestaurantsAdapter(
        onClick = { restaurant ->
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(restaurant)
            )
        }
    )

    private val adapterFilters = FilterAdapter(
        onClick = { filterResponse ->
            viewModel.getRestaurantsBySelectedFilter(filterResponse)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.restaurantsRecyclerView.adapter = adapterRestaurant
        binding.restaurantsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.filterRecyclerView.adapter = adapterFilters
        binding.filterRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.filteredRestaurants.observe(viewLifecycleOwner) {
            adapterRestaurant.updateListRestaurants(it)
        }
        viewModel.filters.observe(viewLifecycleOwner){
            adapterFilters.updateListFilters(it)
        }
    }
}