package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.navigation.findNavController
import com.example.nearbylocator.R
import com.example.nearbylocator.databinding.LayoutHeaderBinding

class HeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutHeaderBinding =
        LayoutHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setupProfileIconNavigation()
    }

    private fun setupProfileIconNavigation() {
        binding.profileIcon.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }

    fun setCityLocation(city: String) {
        binding.cityLocation.text = city
    }

    fun setCurrentLocation(current: String) {
        binding.currentLocation.text = current
    }
}
