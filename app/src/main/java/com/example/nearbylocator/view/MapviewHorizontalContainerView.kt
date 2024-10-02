package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.nearbylocator.R

class MapviewHorizontalContainerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_mapview_horizontal_container, this, true)
        orientation = HORIZONTAL
    }

    // Add any logic here, like dynamic content handling, listeners, etc.
}
