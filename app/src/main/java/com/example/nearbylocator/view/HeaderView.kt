package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.navigation.findNavController
import com.example.nearbylocator.databinding.ViewHeaderBinding

class HeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var binding: ViewHeaderBinding =
        ViewHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        // You can call setupProfileIconNavigation() later with a destination ID
    }

    fun setupProfileIconNavigation(actionId: Int) {
        binding.profileIcon.setOnClickListener {
            val navController = findNavController()
            navController.navigate(actionId)
        }
    }

    fun setCityLocation(city: String) {
        binding.cityLocation.text = city
    }

    fun setCurrentLocation(current: String) {
        binding.currentLocation.text = current
    }
}
