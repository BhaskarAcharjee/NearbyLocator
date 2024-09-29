package com.example.nearbylocator.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nearbylocator.adapters.InstamartImageAdapter
import com.example.nearbylocator.databinding.FragmentCategoryGroupBinding
import com.example.nearbylocator.utils.hotDealsList
import com.example.nearbylocator.utils.instamartSlide1
import com.example.nearbylocator.utils.instamartSlide2
import com.example.nearbylocator.utils.services_hint_Strings
import com.example.nearbylocator.utils.topPicksList

class CategoryGroupFragment : Fragment() {

    // Declare necessary variables
    private lateinit var handler: Handler
    private lateinit var hotDealsAdapter: InstamartImageAdapter
    private lateinit var topPicksAdapter: InstamartImageAdapter
    private lateinit var binding: FragmentCategoryGroupBinding

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

        initializeComponents()// Initialize all UI components
        setCategoryTitle()// Set the category title (from arguments or default)
        setupRecyclerViews()// Set up the RecyclerViews for Hot Deals and Top Picks
        setupScrollView()// Handle scrolling to toggle header visibility
        setupSearchBar()// Set up the search bar with dynamic hints
        setupOffersView()// Set up the offers ViewPager for sliding images
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
        // For first set of images
        binding.offersView.setImageList(instamartSlide1)
        // For second set of images
        binding.offersView2.setImageList(instamartSlide2)
    }

    // Set up RecyclerViews for Hot Deals and Top Picks sections
    private fun setupRecyclerViews() {
        setupHotDealsRecyclerView()   // Set up Hot Deals section
        setupTopPicksRecyclerView()   // Set up Top Picks section
    }

    // Set up Hot Deals RecyclerView with horizontal scrolling
    private fun setupHotDealsRecyclerView() {
        binding.rvHotdeals.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            hotDealsAdapter = InstamartImageAdapter(hotDealsList)
            adapter = hotDealsAdapter
        }
    }

    // Set up Top Picks RecyclerView with horizontal scrolling
    private fun setupTopPicksRecyclerView() {
        binding.rvTopPicks.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            topPicksAdapter = InstamartImageAdapter(topPicksList)
            adapter = topPicksAdapter
        }
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
}
