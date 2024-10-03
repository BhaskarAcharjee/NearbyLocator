package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.nearbylocator.R
import com.example.nearbylocator.model.PlaceTypeIcon

class MapviewHorizontalContainerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val filterMapViewContainer: LinearLayout

    init {
        LayoutInflater.from(context).inflate(R.layout.view_mapview_horizontal_container, this, true)
        orientation = HORIZONTAL
        filterMapViewContainer = findViewById(R.id.filter_mapview_container)
    }

    fun populateFilterMapView(items: List<PlaceTypeIcon>) {
        filterMapViewContainer.removeAllViews()  // Clear existing views

        for (item in items) {
            val itemView = LayoutInflater.from(context).inflate(R.layout.item_filter_mapview, filterMapViewContainer, false)

            val imageView = itemView.findViewById<ImageView>(R.id.place_icon)
            val textView = itemView.findViewById<TextView>(R.id.place_title)

            imageView.setImageResource(item.icon)
            textView.text = item.title

            filterMapViewContainer.addView(itemView)
        }
    }
}
