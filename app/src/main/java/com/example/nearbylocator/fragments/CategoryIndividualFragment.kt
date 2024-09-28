package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.adapters.PlaceCategoryIndividualAdapter
import com.example.nearbylocator.databinding.FragmentCategoryIndividualBinding
import com.example.nearbylocator.utils.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback

class CategoryIndividualFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentCategoryIndividualBinding
    private lateinit var placeCategoryIndividualAdapter: PlaceCategoryIndividualAdapter
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryIndividualBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize components
        setupMapView(savedInstanceState)
        setupSearchBar()
        setupRecyclerViews()
        setupScrollBehavior()
    }

    private fun setupMapView(savedInstanceState: Bundle?) {
        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)  // Asynchronously initialize the map
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        // Set map features (e.g., markers, UI settings, etc.)
    }

    private fun setupRecyclerViews() {
        binding.apply {
            // Setup vertical RecyclerView for more around you
            rvMorearoundyou.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            placeCategoryIndividualAdapter =
                PlaceCategoryIndividualAdapter(restaurantList)
            rvMorearoundyou.adapter = placeCategoryIndividualAdapter
        }
    }

    private fun setupScrollBehavior() {
        binding.rvMorearoundyou.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Get the height of rl_header to use as a threshold
                val linearLayoutHeight = binding.rlHeader.height
                val scrollY = recyclerView.computeVerticalScrollOffset()

                // Toggle visibility based on scroll position
                if (scrollY >= linearLayoutHeight) {
                    binding.llHeader.visibility = View.VISIBLE
                    binding.rlHeader.visibility = View.GONE
                } else {
                    binding.llHeader.visibility = View.GONE
                    binding.rlHeader.visibility = View.VISIBLE
                }
            }
        })
    }


    private fun setupSearchBar() {
        val searchBarView = binding.searchBarView
        searchBarView.setHints(services_hint_Strings)   // Sets up the search bar hints dynamically
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
