package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.LinearLayout
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.QuickDiscoverCategoryAdapter
import com.example.nearbylocator.model.PlaceTypeIcon

class QuickDiscoverCategoryView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_quick_discover_category, this, true)
    }

    fun setCategoryIcons(categoryList: List<PlaceTypeIcon>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_categories)

        // Set GridLayoutManager with 2 rows (span count = 2) and horizontal scrolling
        val layoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        // Set the adapter for RecyclerView
        val adapter = QuickDiscoverCategoryAdapter(context, categoryList)
        recyclerView.adapter = adapter
    }
}
