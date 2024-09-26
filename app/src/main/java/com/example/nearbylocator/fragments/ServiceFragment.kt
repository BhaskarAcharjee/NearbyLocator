package com.example.nearbylocator.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.HoriImageAdapter
import com.example.nearbylocator.adapters.ImageSlideAdapter
import com.example.nearbylocator.adapters.VertiImageAdapter
import com.example.nearbylocator.databinding.FragmentServiceBinding
import com.example.nearbylocator.repository.LocationRepository
import com.example.nearbylocator.utils.exploreFoodsList
import com.example.nearbylocator.utils.getQuicklyFoodsList
import com.example.nearbylocator.utils.homeSlideImages
import com.example.nearbylocator.utils.services_hint_Strings
import com.example.nearbylocator.utils.topRatedFoodsList
import kotlin.math.abs

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
        setupViewPager()
        setupRecyclerViews()
        setupSearchBar()
        setupHeaderView()
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

    // Runnable that moves to the next page in ViewPager
    private val runnable = Runnable {
        viewPager2.currentItem += 1
    }

    // Sets up the ViewPager and its transformer
    private fun setupViewPager() {
        viewPager2 = binding.viewpager2
        handler = Handler(Looper.myLooper()!!)
        imageList = homeSlideImages
        slideAdapter = ImageSlideAdapter(imageList, viewPager2)
        viewPager2.adapter = slideAdapter

        // Adjust page padding and clip settings for proper visibility
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // Setup page transformation animations
        setUpPageTransformer()

        // Auto-slide the view pager every 2.5 seconds
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2500)
            }
        })
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

    // Configures the page transformer for the ViewPager
    private fun setUpPageTransformer() {
        val transformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(90))
            addTransformer { page, position ->
                val scaleFactor = 1 - abs(position)
                page.scaleY = 0.85f + scaleFactor * 0.14f
                page.scaleX = 0.85f + scaleFactor * 0.4f
            }
        }
        viewPager2.setPageTransformer(transformer)
    }

    // Stop automatic sliding when fragment is paused
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    // Resume automatic sliding when fragment is resumed
    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2500)
    }
}
