package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nearbylocator.R
import com.example.nearbylocator.databinding.FragmentChoosePlaceBinding
import com.example.nearbylocator.utils.quickPlaceCategories

class ChoosePlaceFragment : Fragment() {

    private lateinit var binding: FragmentChoosePlaceBinding

    // Define a data class for categories
    data class Category(val name: String, val icon: Int)

    // List to track selected categories
    private val selectedCategories = mutableSetOf<Category>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoosePlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create the category items dynamically
        quickPlaceCategories.forEach { category ->
            val categoryView = LayoutInflater.from(requireContext()).inflate(R.layout.card_place_category, null)

            // Set layout params
            categoryView.layoutParams = GridLayout.LayoutParams().apply {
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
                rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            }

            val categoryIcon = categoryView.findViewById<ImageView>(R.id.categoryIcon)
            val categoryName = categoryView.findViewById<TextView>(R.id.categoryName)

            // Set the category icon and name
            categoryIcon.setImageResource(category.icon)
            categoryName.text = category.name

            // Set click listener for category selection
            categoryView.setOnClickListener {
                toggleCategorySelection(category, categoryView)
            }

            // Add the category item to the GridLayout
            binding.categoryScrollView.findViewById<GridLayout>(R.id.categoryGridLayout).addView(categoryView)
        }

        // Set click listener for the done button
//        binding.doneButton.setOnClickListener {
//            // Navigate back to HomeFragment
//
//        }

        // Set click listener for the back button
        binding.backButton.setOnClickListener {
            // Navigate back to HomeFragment using Navigation Component
            findNavController().navigate(R.id.action_choosePlaceFragment_to_homeFragment)
        }
    }

    private fun toggleCategorySelection(category: Category, categoryView: View) {
        if (selectedCategories.contains(category)) {
            // Deselect category
            selectedCategories.remove(category)
            categoryView.findViewById<ImageView>(R.id.categoryIcon).setBackgroundResource(R.drawable.rounded_corner)
        } else {
            // Check if already selected 5 categories
            if (selectedCategories.size < 5) {
                // Select category
                selectedCategories.add(category)
                categoryView.findViewById<ImageView>(R.id.categoryIcon).setBackgroundResource(R.drawable.rounded_corner_active)
            } else {
                // Optionally show a message to the user
                Toast.makeText(requireContext(), "You can select up to 5 categories", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

