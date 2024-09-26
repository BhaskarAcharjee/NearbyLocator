package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.navigation.findNavController
import com.example.nearbylocator.databinding.ViewHeaderBinding
import com.example.nearbylocator.repository.LocationRepository

class HeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var binding: ViewHeaderBinding =
        ViewHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        // Initialize location observer if needed
    }

    fun setupProfileIconNavigation(actionId: Int) {
        binding.profileIcon.setOnClickListener {
            val navController = findNavController()
            navController.navigate(actionId)
        }
    }

    private fun setCityLocation(city: String) {
        binding.cityLocation.text = city
    }

    private fun setCurrentLocation(current: String) {
        binding.currentLocation.text = current
    }

    fun observeLocationUpdates(locationRepository: LocationRepository) {
        locationRepository.currentCityLocation.observeForever { city ->
            setCityLocation(city)
        }

        locationRepository.currentShortAddress.observeForever { shortAddress ->
            setCurrentLocation(shortAddress)
        }
    }
}
