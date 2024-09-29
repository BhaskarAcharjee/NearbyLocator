package com.example.nearbylocator.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.HoriImageAdapter
import com.example.nearbylocator.adapters.ImageSlideAdapter
import com.example.nearbylocator.adapters.VertiImageAdapter
import com.example.nearbylocator.databinding.FragmentServiceBinding
import com.example.nearbylocator.repository.LocationRepository
import com.example.nearbylocator.utils.exploreFoodsList
import com.example.nearbylocator.utils.getQuicklyFoodsList
import com.example.nearbylocator.utils.serviceOfferImages
import com.example.nearbylocator.utils.services_hint_Strings
import com.example.nearbylocator.utils.topRatedFoodsList

class ServiceFragment : Fragment() {

    // Declare binding and necessary adapters/repositories
    private lateinit var binding: FragmentServiceBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var slideAdapter: ImageSlideAdapter
    private lateinit var topRatedAdapter: HoriImageAdapter
    private lateinit var getQuicklyAdapter: HoriImageAdapter
    private lateinit var exploreAdapter: VertiImageAdapter
    private lateinit var locationRepository: LocationRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize view binding
        binding = FragmentServiceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize necessary components
        setupRecyclerViews()
        setupSearchBar()
        setupHeaderView()
        setupOffersView()
    }

    // Set up the OffersView with the image list
    private fun setupOffersView() {
        val offersView = binding.offersView
        offersView.setImageList(serviceOfferImages)  // Pass the image list to the OffersView
    }

    // Sets up the search bar hints dynamically
    private fun setupSearchBar() {
        val searchBarView = binding.searchBarView
        searchBarView.setHints(services_hint_Strings)
    }

    // Sets up the header view, initializing location-related components
    private fun setupHeaderView() {
        locationRepository = LocationRepository(requireContext())
        val headerView = binding.headerView

        // Observes location updates and sets up profile navigation
        headerView.observeLocationUpdates(locationRepository)
        locationRepository.startLocationUpdates()
        headerView.setupProfileIconNavigation(R.id.action_serviceFragment_to_profileFragment)
    }


    // Configures the RecyclerViews for horizontal and vertical lists
    private fun setupRecyclerViews() {
        binding.apply {
            // Top-rated horizontal list
            rvToprated.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            topRatedAdapter = HoriImageAdapter(topRatedFoodsList)
            rvToprated.adapter = topRatedAdapter

            // Quickly available horizontal list
            rvQuickly.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            getQuicklyAdapter = HoriImageAdapter(getQuicklyFoodsList)
            rvQuickly.adapter = getQuicklyAdapter

            // Explore vertical list
            rvExplore.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            exploreAdapter = VertiImageAdapter(exploreFoodsList)
            rvExplore.adapter = exploreAdapter
        }
    }
}
