package com.example.nearbylocator.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.model.MapviewFavDataClass
import com.example.nearbylocator.utils.mapviewFavDataClasses
import com.example.nearbylocator.view.MapviewFavAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.util.Locale

class MapViewFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var recyclerView: RecyclerView
    private lateinit var mapviewFavAdapter: MapviewFavAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var searchBar: View
    private lateinit var favoriteCards: RecyclerView

    // Declare views for the extended card
    private lateinit var extendedHotelName: TextView
    private lateinit var extendedRating: TextView
    private lateinit var extendedTime: TextView
    private lateinit var extendedType: TextView
    private lateinit var extendedLocation: TextView
//    private lateinit var extendedFoodImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mapview, container, false)

        mapView = view.findViewById(R.id.mapView)
        recyclerView = view.findViewById(R.id.recyclerView)
        searchBar = view.findViewById(R.id.customSearchBar)
        favoriteCards = view.findViewById(R.id.recyclerView)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        // Initialize the extended card views
        extendedHotelName = view.findViewById(R.id.tv_hotel_name_extended)
        extendedRating = view.findViewById(R.id.tv_rating_extended)
        extendedTime = view.findViewById(R.id.tv_time_extended)
        extendedType = view.findViewById(R.id.tv_type_extended)
        extendedLocation = view.findViewById(R.id.tv_hotel_location_extended)

        setupRecyclerView()  // Setup RecyclerView with favorite cards
        setupBottomSheet(view)  // Setup Bottom Sheet behavior

        return view
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        mapviewFavAdapter = MapviewFavAdapter(mapviewFavDataClasses) { position ->
            val selectedPlace = mapviewFavDataClasses[position]
            expandBottomSheet()  // Expand the Bottom Sheet
            populateExtendedCard(selectedPlace)  // Populate extended card
        }
        recyclerView.adapter = mapviewFavAdapter
    }

    private fun populateExtendedCard(selectedPlace: MapviewFavDataClass) {
        extendedHotelName.text = selectedPlace.name
        extendedRating.text = selectedPlace.rating.toString()
        extendedTime.text = selectedPlace.time
        extendedType.text = selectedPlace.type
        extendedLocation.text = selectedPlace.location
    }

    private fun setupBottomSheet(view: View) {
        val bottomSheet: LinearLayout = view.findViewById(R.id.layoutMiscellaneous)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior.peekHeight = 0

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        searchBar.visibility = View.GONE
                        favoriteCards.visibility = View.GONE
                    }

                    BottomSheetBehavior.STATE_COLLAPSED, BottomSheetBehavior.STATE_HIDDEN -> {
                        searchBar.visibility = View.VISIBLE
                        favoriteCards.visibility = View.VISIBLE
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Optionally handle slide changes if needed
            }
        })
    }

    private fun expandBottomSheet() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        // Check permission before requesting location
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permissions if not granted
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                1000
            )
            return
        }

        // Enable "My Location" on the map
        googleMap.isMyLocationEnabled = true

        // Get the user's current location and move the camera to that position
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val userLocation = LatLng(it.latitude, it.longitude)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))

                // Add a marker for the current location
                googleMap.addMarker(
                    MarkerOptions()
                        .position(userLocation)
                        .title("You are here")
                )
            }
        }

        // Set a click listener to add a marker at the clicked position
        googleMap.setOnMapClickListener { latLng ->
            // Clear existing markers if needed (optional)
            googleMap.clear()

            // Reverse geocoding to get place name from latLng
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            var addressText = "Selected Location"

            try {
                // Get the address based on the latitude and longitude
                val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)

                if (addresses != null && addresses.isNotEmpty()) {
                    val address = addresses[0]
                    // Concatenate address components for a readable format
                    addressText = address.getAddressLine(0) ?: "Selected Location"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Add a new marker with the place name as title
            googleMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(addressText)
            )?.showInfoWindow() // Immediately show the info window with the place name

            // Optionally, move the camera to the clicked location
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        }

        // Handle clicking on the "My Location" button
        googleMap.setOnMyLocationButtonClickListener {
            // Move the camera to the user's current location and add a marker
            moveToCurrentLocation()
            true
        }
    }

    // Method to move to the user's current location and place a marker
    private fun moveToCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val userLocation = LatLng(it.latitude, it.longitude)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))

                // Clear any existing markers (optional)
                googleMap.clear()

                // Add a marker at the user's current location
                googleMap.addMarker(
                    MarkerOptions()
                        .position(userLocation)
                        .title("You are here")
                )?.showInfoWindow()
            }
        }
    }


    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
