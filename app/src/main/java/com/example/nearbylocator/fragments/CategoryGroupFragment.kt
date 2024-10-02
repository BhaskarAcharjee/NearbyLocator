package com.example.nearbylocator.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nearbylocator.databinding.FragmentCategoryGroupBinding
import com.example.nearbylocator.utils.instamartSlide2
import com.example.nearbylocator.utils.services_hint_Strings
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

class CategoryGroupFragment : Fragment(), OnMapReadyCallback { // Implement OnMapReadyCallback

    // Declare necessary variables
    private lateinit var handler: Handler
    private lateinit var binding: FragmentCategoryGroupBinding
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    // Inflate the layout using view binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryGroupBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Initialize the fragment view and set up all components
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeComponents() // Initialize all UI components

        // Initialize MapView
        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this) // Register the map ready callback

        setCategoryTitle() // Set the category title (from arguments or default)
        setupScrollView() // Handle scrolling to toggle header visibility
        setupSearchBar() // Set up the search bar with dynamic hints
        setupOffersView() // Set up the offers ViewPager for sliding images
    }

    // Set the category title in both the main and top headers
    private fun setCategoryTitle() {
        val categoryTitle = arguments?.getString("categoryTitle") ?: "Category"
        binding.tvCategoryHeading.text = categoryTitle
        binding.tvCategoryHeadingTop.text = categoryTitle
    }

    // Initialize handler and other necessary components
    private fun initializeComponents() {
        handler = Handler(Looper.myLooper()!!)
    }

    // Set up the offers ViewPager (or custom OffersView) with image lists
    private fun setupOffersView() {
        binding.offersView2.setImageList(instamartSlide2) // For second set of images
    }

    // Toggle header visibility based on scroll position in ScrollView
    private fun setupScrollView() {
        binding.svInstamart.viewTreeObserver.addOnScrollChangedListener {
            val bannerHeight = binding.llBanner.height
            val scrollY = binding.svInstamart.scrollY

            // Show header if scrolled past banner height, hide otherwise
            binding.llHeader.visibility = if (scrollY >= bannerHeight) View.VISIBLE else View.GONE
        }
    }

    // Setup search bar with dynamic hint strings
    private fun setupSearchBar() {
        val searchBarView = binding.searchBarView
        searchBarView.setHints(services_hint_Strings)  // Set hints dynamically
    }

    // Set up the MapView when the map is ready
    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        val location = LatLng(-34.0, 151.0) // Example coordinates
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
    }

    // Ensure you call the appropriate MapView lifecycle methods
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}
