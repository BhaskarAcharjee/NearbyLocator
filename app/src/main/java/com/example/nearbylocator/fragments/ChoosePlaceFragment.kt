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
import com.example.nearbylocator.model.PlaceItem
import com.example.nearbylocator.model.PlaceTypeIcon
import com.example.nearbylocator.constants.PlaceCategoryItems

class ChoosePlaceFragment : Fragment() {

    private lateinit var binding: FragmentChoosePlaceBinding
    private lateinit var adapter: ChoosePlaceCategoryAdapter

    // Shared variable to hold selected categories
    companion object {
        var selectedCategories: MutableSet<PlaceTypeIcon> = mutableSetOf()
        var selectedHeaders: MutableSet<PlaceItem.Header> = mutableSetOf() // Added this line
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
        val categoryItems =
            PlaceCategoryItems.getPlaceCategories() // Assume this returns headers and categories

        adapter = ChoosePlaceCategoryAdapter(categoryItems, ::onCategorySelected) {
            // Handle max selection reached
            Toast.makeText(
                requireContext(),
                "You can select up to 12 categories.",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.categoryRecyclerView.layoutManager = GridLayoutManager(context, 4).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (adapter.getItemViewType(position)) {
                        ChoosePlaceCategoryAdapter.VIEW_TYPE_HEADER -> 4
                        ChoosePlaceCategoryAdapter.VIEW_TYPE_CATEGORY -> 1
                        else -> 1
                    }
                }
            }
        }

        binding.categoryRecyclerView.adapter = adapter
    }

    private fun onCategorySelected(category: PlaceTypeIcon) {
        // Handle category selection
        val selectedHeader = findHeaderForCategory(category)
        selectedHeaders.add(selectedHeader) // Track headers in a Set to avoid duplicates
    }

    private fun findHeaderForCategory(category: PlaceTypeIcon): PlaceItem.Header {
        // Implement logic to find and return the header associated with the category
        return PlaceCategoryItems.getPlaceCategories()
            .filterIsInstance<PlaceItem.CategoryItem>()
            .first { it.place == category }
            .header
    }


    private fun getCategoryHeader(category: PlaceTypeIcon): PlaceItem.Header? {
        // Find and return the header associated with the selected category
        val categoryItems = PlaceCategoryItems.getPlaceCategories()
        for (item in categoryItems) {
            if (item is PlaceItem.Header && item.title == category.title) {
                return item // Return header that matches the category
            }
        }
        return null
    }

}
