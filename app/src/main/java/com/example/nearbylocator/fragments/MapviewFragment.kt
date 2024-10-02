package com.example.nearbylocator.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.databinding.FragmentMapviewBinding
import com.example.nearbylocator.model.MapviewFavDataClass
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

    private lateinit var binding: FragmentMapviewBinding
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mapviewFavAdapter: MapviewFavAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var searchBar: SearchBarView
    private lateinit var favoriteCardsRecyclerView: RecyclerView

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
    ): View {
        binding = FragmentMapviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = binding.mapView
        favoriteCardsRecyclerView = binding.favoriteCardsRecyclerView
        searchBar = binding.searchBarView

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        setupRecyclerView()
        setupBottomSheet(view)
        setupSearchbarView()
    }

    private fun setupSearchbarView() {
        searchBar.setHints(places_hint_Strings) // Update hints dynamically
    }

    private fun setupRecyclerView() {
        favoriteCardsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mapviewFavAdapter = MapviewFavAdapter(mapviewFavDataClasses) { position ->
            val selectedPlace = mapviewFavDataClasses[position]
            expandBottomSheet()
            populateExtendedCard(selectedPlace)
        }
        favoriteCardsRecyclerView.adapter = mapviewFavAdapter
    }

    private fun populateExtendedCard(selectedPlace: MapviewFavDataClass) {
        // Initialize the extended card views
        val layoutMapviewExtended = binding.layoutMapviewExtended
        extendedHotelName = layoutMapviewExtended.tvHotelNameExtended
        extendedRating = layoutMapviewExtended.tvRatingExtended
        extendedTime = layoutMapviewExtended.tvTimeExtended
        extendedType = layoutMapviewExtended.tvTypeExtended
        extendedLocation = layoutMapviewExtended.tvHotelLocationExtended

        extendedHotelName.text = selectedPlace.name
        extendedRating.text = selectedPlace.rating.toString()
        extendedTime.text = selectedPlace.time
        extendedType.text = selectedPlace.type
        extendedLocation.text = selectedPlace.location
    }

    private fun setupBottomSheet(view: View) {
        val bottomSheet: LinearLayout = view.findViewById(R.id.layoutMiscellaneous)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        // No need to set the initial state here since it's gone in XML
        // bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN // This is not needed now
        bottomSheetBehavior.peekHeight = 0

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
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

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    private fun expandBottomSheet() {
        // Set the visibility to VISIBLE before expanding the bottom sheet
        binding.layoutMiscellaneous.visibility = View.VISIBLE
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
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
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
                googleMap.addMarker(MarkerOptions().position(userLocation).title("You are here"))
            }
        }

        // Set a click listener to add a marker at the clicked position
        googleMap.setOnMapClickListener { latLng ->
            googleMap.clear()   // Clear existing markers if needed (optional)
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            var addressText = "Selected Location"

            try {
                // Get the address based on the latitude and longitude
                val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                if (!addresses.isNullOrEmpty()) {
                    addressText = addresses[0].getAddressLine(0) ?: "Selected Location"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            googleMap.addMarker(MarkerOptions().position(latLng).title(addressText))
                ?.showInfoWindow()
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        }

        // Handle clicking on the "My Location" button
        googleMap.setOnMyLocationButtonClickListener {
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
                googleMap.clear()
                googleMap.addMarker(MarkerOptions().position(userLocation).title("You are here"))
                    ?.showInfoWindow()
            }
        }
    }


    // Lifecycle methods for MapView
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

