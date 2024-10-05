package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.QuickPlaceCategoryAdapter
import com.example.nearbylocator.databinding.ViewQuickPlaceCategoryBinding
import com.example.nearbylocator.fragments.HomeFragmentDirections
import com.example.nearbylocator.model.PlaceItem
import com.example.nearbylocator.model.PlaceTypeIcon
import com.example.nearbylocator.utils.defaultPlaceCategoryGroups

class QuickPlaceCategoryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: ViewQuickPlaceCategoryBinding
    private lateinit var quickPlaceCategoryAdapter: QuickPlaceCategoryAdapter

    init {
        orientation = VERTICAL
        binding = ViewQuickPlaceCategoryBinding.inflate(LayoutInflater.from(context), this, true)
        setupRecyclerView()
        setupSeeAllClickListener()
        displayDefaultCategories() // Display the default categories on initialization
    }

    // Set up the RecyclerView and adapter for the quick categories
    private fun setupRecyclerView() {
        quickPlaceCategoryAdapter = QuickPlaceCategoryAdapter(mutableListOf()) { category ->
            when (category) {
                is PlaceTypeIcon -> {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToCategoryIndividualFragment(
                            category.title
                        )
                    findNavController().navigate(action)
                }

                is PlaceItem.Header -> {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToCategoryGroupFragment(category.title)
                    findNavController().navigate(action)
                }
            }
        }
        binding.rvCategories.adapter = quickPlaceCategoryAdapter
        binding.rvCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }


    // Display the default categories in the RecyclerView
    private fun displayDefaultCategories() {
        quickPlaceCategoryAdapter.clearCategories() // Clear any existing categories
        defaultPlaceCategoryGroups.forEach { category ->
            quickPlaceCategoryAdapter.addCategory(category)
        }
    }

    // Click listener for "See All" button to navigate to ChoosePlaceFragment
    private fun setupSeeAllClickListener() {
        binding.placeIcon.setOnClickListener {
            onSeeAllClicked?.invoke() // Trigger the click event for the parent fragment to handle
        }
    }

    fun updateHeaders(selectedHeaders: Set<PlaceItem.Header>) {
        quickPlaceCategoryAdapter.clearCategories()

        if (selectedHeaders.isEmpty()) {
            displayDefaultCategories() // Show default if no header is selected
        } else {
            selectedHeaders.forEach { header ->
                quickPlaceCategoryAdapter.addCategory(header) // Now it supports headers
            }
        }
    }

    // Optional callback for handling "See All" button click
    var onSeeAllClicked: (() -> Unit)? = null
}
