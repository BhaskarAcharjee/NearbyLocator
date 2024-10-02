package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.QuickDiscoverCategoryAdapter
import com.example.nearbylocator.fragments.HomeFragmentDirections
import com.example.nearbylocator.model.PlaceTypeIcon

class QuickDiscoverCategoryView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var quickDiscoverCategoryAdapter: QuickDiscoverCategoryAdapter
    private lateinit var recyclerView: RecyclerView

    // Default categories that show initially
    private val defaultCategories = mutableListOf(
        PlaceTypeIcon("Restaurant", R.drawable.place_category_icon_restaurant),
        PlaceTypeIcon("Bank", R.drawable.place_category_icon_bank),
        PlaceTypeIcon("Grocery Store", R.drawable.place_category_icon_groceries)
    )

    init {
        LayoutInflater.from(context).inflate(R.layout.view_quick_discover_category, this, true)
        setupRecyclerView() // Initialize the RecyclerView and Adapter
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view_categories)

        // Set the click listener here to handle navigation
        quickDiscoverCategoryAdapter = QuickDiscoverCategoryAdapter(context, mutableListOf()) { category ->
            // Handle item click and navigate to CategoryIndividualFragment
            val action = HomeFragmentDirections.actionHomeFragmentToCategoryIndividualFragment(category.title)
            findNavController().navigate(action)
        }

        // Set GridLayoutManager with 2 columns (span count = 2) and horizontal scrolling
        val layoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        // Set the adapter for RecyclerView
        recyclerView.adapter = quickDiscoverCategoryAdapter

        // Display the default categories
        displayDefaultCategories()
    }

    // Display the default categories in the RecyclerView
    private fun displayDefaultCategories() {
        quickDiscoverCategoryAdapter.clearCategories() // Clear any existing categories
        defaultCategories.forEach { category ->
            quickDiscoverCategoryAdapter.addCategory(category)
        }
    }

    // Public method to update categories with selected ones
    fun updateCategories(selectedCategories: MutableSet<PlaceTypeIcon>) {
        quickDiscoverCategoryAdapter.clearCategories()

        if (selectedCategories.isEmpty()) {
            displayDefaultCategories() // Re-display default categories if no selection
        } else {
            selectedCategories.forEach { category ->
                quickDiscoverCategoryAdapter.addCategory(category)
            }
        }
    }

}

