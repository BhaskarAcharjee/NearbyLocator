package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.MapviewFilterPlaceCategoryAdapter
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
        savedImageView = fixedImageContainer.findViewById(R.id.saved_image_view) // Assuming you give it an id in XML
        expandImageView = fixedImageContainer.findViewById(R.id.expand_image_view) // Assuming you give it an id in XML

        setupClickListeners() // Set up click listeners for icons
    }

    private fun setupClickListeners() {
        // Click listener for saved icon
        savedImageView.setOnClickListener {
            isSaved = !isSaved // Toggle saved state
            updateSavedIcon()
        }

        // Click listener for expand/minimize icon
        expandImageView.setOnClickListener {
            isExpanded = !isExpanded // Toggle expand state
            updateExpandIcon()
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

    fun setupRecyclerView(selectedCategories: MutableSet<PlaceTypeIcon>) {
        val categories = getCategoriesToDisplay(selectedCategories)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        adapter = MapviewFilterPlaceCategoryAdapter(categories) { position ->
            // Handle item click if needed
        }
        recyclerView.adapter = adapter
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
}
