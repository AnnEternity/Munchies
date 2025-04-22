package com.example.fooddelivery.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil3.load
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    private lateinit var binding: DetailFragmentBinding
    private val viewModel: DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isOpen.observe(viewLifecycleOwner) {
            binding.isOpenText.text = when (it) {
                true -> {
                    getString(R.string.open)
                }

                false -> {
                    getString(R.string.closed)
                }

                else -> {
                    getString(R.string.unknown)
                }
            }
            binding.isOpenText.setTextColor(
                when (it) {
                    true -> {
                        ContextCompat.getColor(requireContext(), R.color.Positive)
                    }

                    false -> {
                        ContextCompat.getColor(requireContext(), R.color.Negative)
                    }

                    else -> {
                        ContextCompat.getColor(requireContext(), R.color.DarkText)
                    }
                }
            )
        }

        binding.detailActivity = args.restaurant
        binding.bannerImage.load(args.restaurant.imageUrl)

        binding.chevronImage.setOnClickListener {
            findNavController().navigateUp()
        }

    }

}