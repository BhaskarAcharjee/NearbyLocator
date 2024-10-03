package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.MapviewFilterPlaceCategoryAdapter
import com.example.nearbylocator.model.PlaceTypeIcon

class MapviewHorizontalContainerView(context: Context, attrs: AttributeSet) : HorizontalScrollView(context, attrs) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MapviewFilterPlaceCategoryAdapter

    init {
        LayoutInflater.from(context).inflate(R.layout.view_mapview_horizontal_container, this)
        recyclerView = findViewById(R.id.recyclerViewHorizontalFilter)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = MapviewFilterPlaceCategoryAdapter(emptyList()) // Initially empty
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

    fun populateFilterMapView(categories: List<PlaceTypeIcon>) {
        adapter.updateData(categories)
    }
}

