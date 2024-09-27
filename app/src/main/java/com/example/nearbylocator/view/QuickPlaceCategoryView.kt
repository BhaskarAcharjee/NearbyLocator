package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.QuickPlaceCategoryAdapter
import com.example.nearbylocator.databinding.LayoutQuickPlaceCategoryBinding
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
    }

    // Set up the RecyclerView and adapter for the quick categories
    private fun setupRecyclerView() {
        quickPlaceCategoryAdapter = QuickPlaceCategoryAdapter(defaultCategories) { category ->
            // Handle item click, navigate or perform actions based on the category
            // This can be customized based on navigation needs
        }
        binding.rvCategories.adapter = quickPlaceCategoryAdapter
        binding.rvCategories.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    // Click listener for "See All" button to navigate to ChoosePlaceFragment
    private fun setupSeeAllClickListener() {
        binding.placeIcon.setOnClickListener {
            // Assuming there is a fragment manager or activity to handle this navigation
            // Can use a callback or directly navigate
            onSeeAllClicked?.invoke() // Trigger the click event for the parent fragment to handle
        }
    }

    // Public method to update categories with selected ones
    fun updateCategories(selectedCategories: MutableSet<PlaceTypeIconDataClass>) {
        quickPlaceCategoryAdapter.clearCategories()

        if (selectedCategories.isEmpty()) {
            // Re-add default categories if no selection
            defaultCategories.forEach { category ->
                quickPlaceCategoryAdapter.addCategory(category)
            }
        } else {
            // Convert and add the selected categories from ChoosePlaceFragment
            selectedCategories.forEach { category ->
                // Convert ChoosePlaceCategoryDataClass to QuickPlaceCategoryDataClass
                val quickCategory = PlaceTypeIconDataClass(category.title, category.icon) // Ensure these properties exist
                quickPlaceCategoryAdapter.addCategory(quickCategory)
            }
        }
        quickPlaceCategoryAdapter.notifyDataSetChanged() // Notify adapter about the data change
    }


    // Optional callback for handling "See All" button click
    var onSeeAllClicked: (() -> Unit)? = null
}