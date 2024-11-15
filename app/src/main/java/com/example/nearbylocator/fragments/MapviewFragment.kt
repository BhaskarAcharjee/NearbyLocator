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
import com.example.nearbylocator.adapters.AutocompleteAdapter
import com.example.nearbylocator.databinding.FragmentMapviewBinding
import com.example.nearbylocator.model.MapviewFavDataClass
import com.example.nearbylocator.utils.mapviewFavDataClasses
import com.example.nearbylocator.utils.places_hint_Strings
import com.example.nearbylocator.view.MapviewFavAdapter
import com.example.nearbylocator.view.MapviewHorizontalContainerView
import com.example.nearbylocator.view.SearchBarView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.util.Locale

class MapViewFragment : Fragment(), OnMapReadyCallback {

    // View bindings and Google Map components
    private lateinit var binding: FragmentMapviewBinding
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var placesClient: PlacesClient
    private lateinit var autocompleteAdapter: AutocompleteAdapter

    // UI Components
    private lateinit var mapviewFavAdapter: MapviewFavAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var searchBar: SearchBarView
    private lateinit var favoriteCardsRecyclerView: RecyclerView
    private lateinit var horizontalContainerView: MapviewHorizontalContainerView
    private lateinit var autocompleteRecyclerView: RecyclerView

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

        // Initialize Places API
        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), getString(R.string.google_maps_key))
        }
        placesClient = Places.createClient(requireContext())


        setupUIComponents(view)
        setupMapView(savedInstanceState)
        setupRecyclerView()
        setupBottomSheetBehavior()
        setupSearchBarHints()
        setupLocationButton()
        setupSearchBarSearchListener()// Listen to search input
    }

    private fun setupSearchBarSearchListener() {
        searchBar.setOnTextChangedListener { query ->
            if (query.isNotEmpty()) {
                fetchAutocompleteSuggestions(query) // Fetch suggestions based on user input
            } else {
                autocompleteRecyclerView.visibility =
                    View.GONE // Hide suggestions if input is empty
            }
        }
    }

    private fun searchForLocation(query: String) {
        try {
            val geocoder = Geocoder(requireContext())
            val addresses = geocoder.getFromLocationName(query, 1)

            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0]
                val latLng = LatLng(address.latitude, address.longitude)

                // Update map location and add marker
                updateMapLocation(latLng, address.getAddressLine(0))
            } else {
                Toast.makeText(requireContext(), "Location not found", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error finding location", Toast.LENGTH_SHORT).show()
        }
    }


    // Set up the map view and location services
    private fun setupMapView(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    // Update setupUIComponents method
    private fun setupUIComponents(view: View) {
        mapView = binding.mapView
        favoriteCardsRecyclerView = binding.favoriteCardsRecyclerView
        searchBar = binding.searchBarView
        horizontalContainerView = binding.mapviewHorizontalContainerView
        autocompleteRecyclerView = searchBar.findViewById(R.id.autocompleteRecyclerView)

        autocompleteAdapter = AutocompleteAdapter(emptyList()) { prediction ->
            // Handle prediction click
            searchBar.updateEditText(prediction.getFullText(null).toString())
            searchForLocation(prediction.getFullText(null).toString())
            autocompleteRecyclerView.visibility = View.GONE // Hide suggestions on click
        }

        autocompleteRecyclerView.layoutManager = LinearLayoutManager(context)
        autocompleteRecyclerView.adapter = autocompleteAdapter
    }

    private fun fetchAutocompleteSuggestions(query: String) {
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response ->
                val predictions = response.autocompletePredictions
                autocompleteAdapter.updateData(predictions)
                autocompleteRecyclerView.visibility =
                    if (predictions.isNotEmpty()) View.VISIBLE else View.GONE
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    requireContext(),
                    "Error fetching suggestions: $exception",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


    // Update the setupRecyclerView method
    private fun setupRecyclerView() {
        favoriteCardsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        mapviewFavAdapter = MapviewFavAdapter(mapviewFavDataClasses) { position ->
            val selectedPlace = mapviewFavDataClasses[position]
            expandBottomSheet()
            populateExtendedCard(selectedPlace)
        }
        favoriteCardsRecyclerView.adapter = mapviewFavAdapter

        // Setup custom horizontal container view
        horizontalContainerView.setupRecyclerView(ChoosePlaceFragment.selectedCategories)
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

    // Method to update the map location and add a marker
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
