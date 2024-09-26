package com.example.nearbylocator.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import java.util.*

class LocationRepository(private val context: Context) {

    private val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("location_prefs", Context.MODE_PRIVATE)

    private val _currentCityLocation = MutableLiveData<String>()
    val currentCityLocation: LiveData<String> get() = _currentCityLocation

    private val _currentShortAddress = MutableLiveData<String>()
    val currentShortAddress: LiveData<String> get() = _currentShortAddress

    init {
        // Load the last stored values from SharedPreferences when the repository is initialized
        loadLastKnownLocation()
    }

    private fun loadLastKnownLocation() {
        val city = sharedPreferences.getString("city_location", "Fetching location...")
            ?: "Fetching location..."
        val shortAddress = sharedPreferences.getString("short_address", "Fetching address...")
            ?: "Fetching address..."
        _currentCityLocation.postValue(city)
        _currentShortAddress.postValue(shortAddress)
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000 // 10 seconds
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val location: Location? = locationResult.lastLocation
            if (location != null) {
                getCityAndShortAddress(location.latitude, location.longitude)
            }
        }
    }

    private fun getCityAndShortAddress(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addressList: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addressList.isNullOrEmpty()) {
                val address: Address = addressList[0]
                val city = address.locality ?: "Unknown City"
                val shortAddress = "${address.featureName ?: ""}, ${address.thoroughfare ?: ""}"

                // Save the data to LiveData
                _currentCityLocation.postValue(city)
                _currentShortAddress.postValue(shortAddress)

                // Save the data to SharedPreferences
                sharedPreferences.edit().apply {
                    putString("city_location", city)
                    putString("short_address", shortAddress)
                    apply()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }
}
