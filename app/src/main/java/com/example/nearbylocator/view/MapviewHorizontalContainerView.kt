package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.MapviewFilterHeaderAdapter
import com.example.nearbylocator.adapters.MapviewFilterPlaceCategoryAdapter
import com.example.nearbylocator.fragments.ChoosePlaceFragment
import com.example.nearbylocator.model.PlaceItem
import com.example.nearbylocator.model.PlaceTypeIcon

class MapviewHorizontalContainerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val recyclerView: RecyclerView
    private lateinit var adapter: MapviewFilterPlaceCategoryAdapter
    private val fixedImageContainer: LinearLayout
    private val savedImageView: ImageView
    private val expandImageView: ImageView

    // Track the state of the saved and expand icons
    private var isSaved: Boolean = false
    private var isExpanded: Boolean = false

    init {
        LayoutInflater.from(context).inflate(R.layout.view_mapview_horizontal_container, this, true)
        recyclerView = findViewById(R.id.recyclerViewHorizontalFilter)
        fixedImageContainer = findViewById(R.id.fixed_image_container)
        savedImageView = fixedImageContainer.findViewById(R.id.saved_image_view)
        expandImageView = fixedImageContainer.findViewById(R.id.expand_image_view)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        savedImageView.setOnClickListener {
            isSaved = !isSaved
            updateSavedIcon()
        }

        expandImageView.setOnClickListener {
            isExpanded = !isExpanded
            updateExpandIcon()
            toggleRecyclerViewContent(isExpanded)
        }
    }

    private fun updateSavedIcon() {
        if (isSaved) {
            savedImageView.setColorFilter(resources.getColor(R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN)
        } else {
            savedImageView.clearColorFilter()
        }
    }

    private fun updateExpandIcon() {
        if (isExpanded) {
            expandImageView.setImageResource(R.drawable.ic_minimize)
        } else {
            expandImageView.setImageResource(R.drawable.ic_expand)
        }
    }

    private fun toggleRecyclerViewContent(isExpanded: Boolean) {
        if (isExpanded) {
            val headers = getSelectedHeaders(ChoosePlaceFragment.selectedHeaders)
            updateRecyclerViewWithHeaders(headers)
        } else {
            val categories = getCategoriesToDisplay(ChoosePlaceFragment.selectedCategories)
            updateRecyclerViewWithCategories(categories)
        }
    }

    private fun getCategoriesToDisplay(selectedCategories: MutableSet<PlaceTypeIcon>): List<PlaceTypeIcon> {
        return if (selectedCategories.isNotEmpty()) {
            selectedCategories.toList()
        } else {
            listOf(
                PlaceTypeIcon("Hospital", R.drawable.place_category_icon_hospital),
                PlaceTypeIcon("Cafe", R.drawable.place_category_icon_cafe),
                PlaceTypeIcon("Supermarket", R.drawable.place_category_icon_supermarket),
                PlaceTypeIcon("Gym", R.drawable.place_category_icon_gym),
                PlaceTypeIcon("Bus Stop", R.drawable.place_category_icon_busstop),
                PlaceTypeIcon("Restaurant", R.drawable.place_category_icon_restaurant),
                PlaceTypeIcon("Bank", R.drawable.place_category_icon_bank),
                PlaceTypeIcon("Grocery Store", R.drawable.place_category_icon_groceries),
            )
        }
    }

    private fun getSelectedHeaders(selectedHeaders: MutableSet<PlaceItem.Header>): List<PlaceItem.Header> {
        return if (selectedHeaders.isNotEmpty()) {
            selectedHeaders.toList()
        } else {
            listOf(
                PlaceItem.Header("Food & Drinks", R.drawable.place_category_icon_restaurant),
                PlaceItem.Header("Financial Services", R.drawable.place_category_icon_bank),
                PlaceItem.Header("Health & Wellness", R.drawable.place_category_icon_hospital)
            )
        }
    }

    // Update RecyclerView with category icons
    private fun updateRecyclerViewWithCategories(categories: List<PlaceTypeIcon>) {
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = MapviewFilterPlaceCategoryAdapter(categories) { position ->
            // Handle category item click
        }
        recyclerView.adapter = adapter
    }

    // Update RecyclerView with header icons and text
    private fun updateRecyclerViewWithHeaders(headers: List<PlaceItem.Header>) {
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val headerAdapter = MapviewFilterHeaderAdapter(headers) { position ->
            // Handle header item click
        }
        recyclerView.adapter = headerAdapter
    }

    // Method to be called from Fragment to setup the RecyclerView
    fun setupRecyclerView(selectedCategories: MutableSet<PlaceTypeIcon>) {
        val categories = getCategoriesToDisplay(selectedCategories)
        updateRecyclerViewWithCategories(categories)
    }
}