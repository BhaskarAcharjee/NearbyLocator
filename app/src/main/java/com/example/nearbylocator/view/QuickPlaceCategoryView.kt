package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.QuickPlaceCategoryAdapter
import com.example.nearbylocator.databinding.LayoutQuickPlaceCategoryBinding
import com.example.nearbylocator.fragments.HomeFragmentDirections
import com.example.nearbylocator.model.PlaceTypeIconDataClass

class QuickPlaceCategoryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutQuickPlaceCategoryBinding
    private lateinit var quickPlaceCategoryAdapter: QuickPlaceCategoryAdapter

    // Default categories that show initially
    private val defaultCategories = mutableListOf(
        PlaceTypeIconDataClass("Restaurant", R.drawable.resturant_icon),
        PlaceTypeIconDataClass("Bank", R.drawable.bank_icon),
        PlaceTypeIconDataClass("Groceries", R.drawable.groceries_icon)
    )

    init {
        orientation = VERTICAL
        binding = LayoutQuickPlaceCategoryBinding.inflate(LayoutInflater.from(context), this, true)
        setupRecyclerView()
        setupSeeAllClickListener()
        displayDefaultCategories() // Display the default categories on initialization
    }

    // Set up the RecyclerView and adapter for the quick categories
    private fun setupRecyclerView() {
        quickPlaceCategoryAdapter = QuickPlaceCategoryAdapter(mutableListOf()) { category ->
            // Handle item click, navigate to CategoryIndividualFragment with category type
            val action = HomeFragmentDirections.actionHomeFragmentToCategoryIndividualFragment(category.title)
            findNavController().navigate(action)
        }
        binding.rvCategories.adapter = quickPlaceCategoryAdapter
        binding.rvCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }


    // Display the default categories in the RecyclerView
    private fun displayDefaultCategories() {
        quickPlaceCategoryAdapter.clearCategories() // Clear any existing categories
        defaultCategories.forEach { category ->
            quickPlaceCategoryAdapter.addCategory(category)
        }
    }

    // Click listener for "See All" button to navigate to ChoosePlaceFragment
    private fun setupSeeAllClickListener() {
        binding.placeIcon.setOnClickListener {
            onSeeAllClicked?.invoke() // Trigger the click event for the parent fragment to handle
        }
    }

    // Public method to update categories with selected ones
    fun updateCategories(selectedCategories: MutableSet<PlaceTypeIconDataClass>) {
        quickPlaceCategoryAdapter.clearCategories()

        if (selectedCategories.isEmpty()) {
            displayDefaultCategories() // Re-display default categories if no selection
        } else {
            selectedCategories.forEach { category ->
                val quickCategory = PlaceTypeIconDataClass(category.title, category.icon)
                quickPlaceCategoryAdapter.addCategory(quickCategory)
            }
        }
        quickPlaceCategoryAdapter.notifyDataSetChanged() // Notify adapter about the data change
    }

    // Optional callback for handling "See All" button click
    var onSeeAllClicked: (() -> Unit)? = null
}
