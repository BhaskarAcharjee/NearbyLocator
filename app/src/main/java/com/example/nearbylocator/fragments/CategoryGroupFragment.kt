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
import com.example.nearbylocator.adapters.ImageSlideAdapter
import com.example.nearbylocator.adapters.InstamartImageAdapter
import com.example.nearbylocator.databinding.FragmentCategoryGroupBinding
import com.example.nearbylocator.utils.*
import kotlin.math.abs

class CategoryGroupFragment : Fragment() {

    // Variables for image slides and adapters
    private lateinit var handler: Handler
    private lateinit var slideAdapter1: ImageSlideAdapter
    private lateinit var slideAdapter2: ImageSlideAdapter
    private lateinit var hotDealsAdapter: InstamartImageAdapter
    private lateinit var topPicksAdapter: InstamartImageAdapter
    private lateinit var imageList1: ArrayList<Int>
    private lateinit var imageList2: ArrayList<Int>
    private lateinit var viewPager1: ViewPager2
    private lateinit var viewPager2: ViewPager2
    private lateinit var binding: FragmentCategoryGroupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using view binding
        binding = FragmentCategoryGroupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize components
        initializeComponents()

        // Setup image sliders (ViewPagers)
        setupViewPagers()

        // Setup Hot Deals and Top Picks RecyclerViews
        setupRecyclerViews()

        // Handle scroll changes in the scroll view for banner visibility
        setupScrollView()

        setupSearchBar()

    }

    // Initialize necessary components (e.g., handler, lists, adapters)
    private fun initializeComponents() {
        handler = Handler(Looper.myLooper()!!)
        imageList1 = instamartSlide1
        imageList2 = instamartSlide2
    }

    // Setup ViewPagers for image sliders
    private fun setupViewPagers() {
        viewPager1 = binding.viewpager1
        viewPager2 = binding.viewpager2

        // Initialize slide adapters
        slideAdapter1 = ImageSlideAdapter(imageList1, viewPager1)
        slideAdapter2 = ImageSlideAdapter(imageList2, viewPager2)

        // Set adapters and basic settings for both ViewPagers
        viewPager1.adapter = slideAdapter1
        viewPager2.adapter = slideAdapter2

        configureViewPager(viewPager1)
        configureViewPager(viewPager2)

        // Add a page change listener to auto-slide images
        setupAutoSlide(viewPager1)
        setupAutoSlide(viewPager2)

        // Set up custom page transformer for both ViewPagers
        setUpPageTransformer()
    }

    // Common configurations for both ViewPagers (disable overscroll, set offscreen limit)
    private fun configureViewPager(viewPager: ViewPager2) {
        viewPager.offscreenPageLimit = 3
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    // Set up a composite page transformer to create the scaling effect while swiping
    private fun setUpPageTransformer() {
        val transformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(90))  // Set page margins
            addTransformer { page, position ->
                val scaleFactor = 1 - abs(position)
                page.scaleY = 0.85f + scaleFactor * 0.14f  // Scale vertically
                page.scaleX = 0.85f + scaleFactor * 0.4f   // Scale horizontally
            }
        }
        viewPager1.setPageTransformer(transformer)
        viewPager2.setPageTransformer(transformer)
    }

    // Auto-slide functionality: automatically moves to the next page after a delay
    private fun setupAutoSlide(viewPager: ViewPager2) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(slideRunnable)  // Stop any previous callback
                handler.postDelayed(slideRunnable, 2500)  // Slide after 2.5 seconds
            }
        })
    }

    // Runnable to change pages in ViewPagers automatically
    private val slideRunnable = Runnable {
        viewPager1.currentItem += 1
        viewPager2.currentItem += 1
    }

    // Set up RecyclerViews for Hot Deals and Top Picks sections
    private fun setupRecyclerViews() {
        // Hot Deals section
        binding.rvHotdeals.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            hotDealsAdapter = InstamartImageAdapter(hotDealsList)
            adapter = hotDealsAdapter
        }

        // Top Picks section
        binding.rvTopPicks.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
