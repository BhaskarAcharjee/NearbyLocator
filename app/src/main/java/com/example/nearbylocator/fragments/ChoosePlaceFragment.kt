package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.ChoosePlaceCategoryAdapter
import com.example.nearbylocator.databinding.FragmentChoosePlaceBinding
import com.example.nearbylocator.model.PlaceTypeIcon
import com.example.nearbylocator.utils.PlaceCategoryItems

class ChoosePlaceFragment : Fragment() {

    private lateinit var binding: FragmentChoosePlaceBinding
    private lateinit var adapter: ChoosePlaceCategoryAdapter

    // Shared variable to hold selected categories
    companion object {
        var selectedCategories: MutableSet<PlaceTypeIcon> = mutableSetOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoosePlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCategoryGrid()

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_choosePlaceFragment_to_homeFragment)
        }

        binding.doneButton.setOnClickListener {
            val selectedCategories = adapter.getSelectedCategories()
            when {
                selectedCategories.size < 8 -> {
                    Toast.makeText(
                        requireContext(),
                        "Please select at least 8 categories.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    // Update the shared variable with selected categories
                    Companion.selectedCategories.clear()
                    Companion.selectedCategories.addAll(selectedCategories)
                    findNavController().navigate(R.id.action_choosePlaceFragment_to_homeFragment)
                }
            }
        }
    }

    private fun setupCategoryGrid() {
        val categoryItems = PlaceCategoryItems.getPlaceCategories()

        adapter = ChoosePlaceCategoryAdapter(categoryItems, ::onCategorySelected) {
            // Callback when the user selects more than 12 categories
            Toast.makeText(
                requireContext(),
                "You can select up to 12 categories.",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.categoryRecyclerView.layoutManager = GridLayoutManager(context, 3).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (adapter.getItemViewType(position)) {
                        ChoosePlaceCategoryAdapter.VIEW_TYPE_HEADER -> 3  // Header takes all 3 columns
                        ChoosePlaceCategoryAdapter.VIEW_TYPE_CATEGORY -> 1  // Category takes 1 column
                        else -> 1
                    }
                }
            }
        }

        binding.categoryRecyclerView.adapter = adapter
    }

    private fun onCategorySelected(category: PlaceTypeIcon) {
        // Handle category selection if needed
    }
}

