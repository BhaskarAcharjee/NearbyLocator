package com.example.nearbylocator.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.MapviewFilterPlaceCategoryAdapter
import com.example.nearbylocator.databinding.FragmentMapviewBinding
import com.example.nearbylocator.model.MapviewFavDataClass
import com.example.nearbylocator.model.PlaceTypeIcon
import com.example.nearbylocator.utils.mapviewFavDataClasses
import com.example.nearbylocator.utils.places_hint_Strings
import com.example.nearbylocator.view.MapviewFavAdapter
import com.example.nearbylocator.view.SearchBarView
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

    // View bindings and Google Map components
    private lateinit var binding: FragmentMapviewBinding
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // UI Components
    private lateinit var mapviewFavAdapter: MapviewFavAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var searchBar: SearchBarView
    private lateinit var favoriteCardsRecyclerView: RecyclerView

    // Extended card views
    private lateinit var extendedHotelName: TextView
    private lateinit var extendedRating: TextView
    private lateinit var extendedTime: TextView
    private lateinit var extendedType: TextView
    private lateinit var extendedLocation: TextView

    // Lifecycle method to inflate layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Lifecycle method to initialize views and map
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUIComponents(view)
        setupMapView(savedInstanceState)
        setupRecyclerView()
        setupBottomSheetBehavior()
        setupSearchBarHints()
        setupLocationButton()
    }

    // Set up and initialize the required UI components
    private fun setupUIComponents(view: View) {
        mapView = binding.mapView
        favoriteCardsRecyclerView = binding.favoriteCardsRecyclerView
        searchBar = binding.searchBarView
    }

    // Set up the map view and location services
    private fun setupMapView(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    // Set up the RecyclerView to display favorite places
    private fun setupRecyclerView() {
        favoriteCardsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        mapviewFavAdapter = MapviewFavAdapter(mapviewFavDataClasses) { position ->
            val selectedPlace = mapviewFavDataClasses[position]
            expandBottomSheet()
            populateExtendedCard(selectedPlace)
        }
        favoriteCardsRecyclerView.adapter = mapviewFavAdapter

        val recyclerView = binding.recyclerViewHorizontalFilter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val categoriesToDisplay = getCategoriesToDisplay()
        val adapter = MapviewFilterPlaceCategoryAdapter(categoriesToDisplay)
        recyclerView.adapter = adapter
    }

    // Helper method to get the categories to display
    private fun getCategoriesToDisplay(): List<PlaceTypeIcon> {
        return if (ChoosePlaceFragment.selectedCategories.isNotEmpty()) {
            ChoosePlaceFragment.selectedCategories.toList()
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

    // Set up bottom sheet behavior to expand/collapse details
    private fun setupBottomSheetBehavior() {
        val bottomSheet: LinearLayout = binding.layoutMiscellaneous
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.peekHeight = 0

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                toggleSearchBarVisibility(newState)
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    // Set up hints for the search bar
    private fun setupSearchBarHints() {
        searchBar.setHints(places_hint_Strings)
    }

    // Toggle visibility of search bar and favorite list based on bottom sheet state
    private fun toggleSearchBarVisibility(newState: Int) {
        when (newState) {
            BottomSheetBehavior.STATE_EXPANDED -> {
                searchBar.visibility = View.GONE
                favoriteCardsRecyclerView.visibility = View.GONE
            }

            BottomSheetBehavior.STATE_COLLAPSED, BottomSheetBehavior.STATE_HIDDEN -> {
                searchBar.visibility = View.VISIBLE
                favoriteCardsRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    // Populate extended card with selected place details
    private fun populateExtendedCard(selectedPlace: MapviewFavDataClass) {
        binding.layoutMapviewExtended.apply {
            extendedHotelName = tvHotelNameExtended
            extendedRating = tvRatingExtended
            extendedTime = tvTimeExtended
            extendedType = tvTypeExtended
            extendedLocation = tvHotelLocationExtended

            extendedHotelName.text = selectedPlace.name
            extendedRating.text = selectedPlace.rating.toString()
            extendedTime.text = selectedPlace.time
            extendedType.text = selectedPlace.type
            extendedLocation.text = selectedPlace.location
        }
    }

    // Set up location button to move to the user's current location
    private fun setupLocationButton() {
        binding.fabCurrentLocation.setOnClickListener { moveToCurrentLocation() }
    }

    // Move to the user's current location and place a marker
    private fun moveToCurrentLocation() {
        try {
            if (checkLocationPermissions()) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        updateMapLocation(
                            LatLng(it.latitude, it.longitude),
                            "You are here"
                        )
                    }
                }
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
            showError("Location permission is required to access current location.")
        }
    }


    // Check for location permissions
    private fun checkLocationPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1000
            )
            return false
        }
        return true
    }

    // Update the map location with a marker
    private fun updateMapLocation(latLng: LatLng, title: String) {
        googleMap.apply {
            moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            clear() // Clear previous markers if needed
            addMarker(MarkerOptions().position(latLng).title(title))?.showInfoWindow()
        }
    }

    // Handle map click to add a marker at the clicked position
    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        try {
            if (checkLocationPermissions()) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        updateMapLocation(LatLng(it.latitude, it.longitude), "You are here")
                    }
                }
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
            showError("Location permission is required to access current location.")
        }

        googleMap.setOnMapClickListener { latLng ->
            val addressText = getAddressFromLatLng(latLng) ?: "Selected Location"
            updateMapLocation(latLng, addressText)
        }
    }


    // Get the address based on latitude and longitude
    private fun getAddressFromLatLng(latLng: LatLng): String? {
        return try {
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            addresses?.firstOrNull()?.getAddressLine(0)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    // Expand the bottom sheet to show place details
    private fun expandBottomSheet() {
        binding.layoutMiscellaneous.visibility = View.VISIBLE
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    // Lifecycle methods for managing MapView state
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
        if (::mapView.isInitialized) {
            mapView.onDestroy()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        if (::mapView.isInitialized) {
            mapView.onLowMemory()
        }
    }
}
