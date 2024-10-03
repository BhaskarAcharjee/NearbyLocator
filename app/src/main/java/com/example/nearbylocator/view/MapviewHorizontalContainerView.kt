package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
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

    init {
        LayoutInflater.from(context).inflate(R.layout.view_mapview_horizontal_container, this, true)
        recyclerView = findViewById(R.id.recyclerViewHorizontalFilter)
    }

    fun setupRecyclerView(selectedCategories: MutableSet<PlaceTypeIcon>) {
        val categories = getCategoriesToDisplay(selectedCategories)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = MapviewFilterPlaceCategoryAdapter(categories)
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

