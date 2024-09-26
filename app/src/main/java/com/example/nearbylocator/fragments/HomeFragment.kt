package com.example.nearbylocator.fragments

import QuickPlaceCategoryAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.PlaceCategoryGroupAdapter
import com.example.nearbylocator.adapters.ImageSlideAdapter
import com.example.nearbylocator.databinding.FragmentHomeBinding
import com.example.nearbylocator.model.QuickPlaceCategoryDataClass
import com.example.nearbylocator.utils.dineoutBestOffersList
import com.example.nearbylocator.utils.places_hint_Strings
import kotlin.math.abs

class HomeFragment : Fragment() {

    // Declare variables for views, adapters, and handlers
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var slideAdapter: ImageSlideAdapter

    // onCreateView inflates the layout and returns the root view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    // onViewCreated is called after the fragment's view has been created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get reference to HeaderView
        val headerView = binding.headerView

        // Set city and current location
        headerView.setCityLocation(getString(R.string.city_location))
        headerView.setCurrentLocation(getString(R.string.current_location))
        headerView.setupProfileIconNavigation(R.id.action_homeFragment_to_profileFragment)

        // Access custom SearchBarView
        val searchBarView = binding.searchBarView
        // Update hints dynamically
         searchBarView.setHints(places_hint_Strings)

        // Call setup methods for different sections
        setupViewPager() // Set up the image slider
        setupQuickCategoryNavigation() // Merge into one function
        setupChoosePlaceCategory()
        updateQuickPlaceCategories() // Update quick categories when the fragment is created
        setupPlaceCategories() // Set up multiple place categories like Food & Drinks, Shopping, etc.
    }

    // Setting up ViewPager2 for image slider with transformer
    private fun setupViewPager() {
        viewPager2 = binding.viewpager2
        handler = Handler(Looper.myLooper()!!)
        imageList = arrayListOf(
            R.drawable.homeslide1,
            R.drawable.homeslide2,
            R.drawable.homeslide3,
            R.drawable.homeslide4
        )
        slideAdapter = ImageSlideAdapter(imageList, viewPager2)
        viewPager2.adapter = slideAdapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        setUpTransformer() // Set up page transformations for a smooth animation

        // Auto-scroll images
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2500)
            }
        })
    }

    // Setting up the transformer to add cool page transition effects for ViewPager2
    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
            page.scaleX = 0.85f + r * 0.3f
        }
        viewPager2.setPageTransformer(transformer)
    }

    // Runnable for automatic page sliding
    private val runnable = Runnable {
        viewPager2.currentItem += 1
    }

    // Merged Quick Category Navigation setup
    private fun setupQuickCategoryNavigation() {
        // Define default category data
        val defaultCategories = mutableListOf(
            QuickPlaceCategoryDataClass("Restaurant", R.drawable.resturant_icon),
            QuickPlaceCategoryDataClass("Bank", R.drawable.bank_icon),
            QuickPlaceCategoryDataClass("Groceries", R.drawable.groceries_icon)
        )

        val quickPlaceCategoryAdapter = QuickPlaceCategoryAdapter(defaultCategories) { category ->
            // Handle the click on a category, you can navigate to a new fragment based on category
            when (category.title) {
                "Restaurant" -> findNavController().navigate(R.id.action_homeFragment_to_categoryIndividualFragment)
                "Bank" -> findNavController().navigate(R.id.action_homeFragment_to_categoryIndividualFragment)
                // Add more cases as needed
            }
        }

        binding.quickPlaceCategory.rvCategories.adapter = quickPlaceCategoryAdapter
        binding.quickPlaceCategory.rvCategories.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun updateQuickPlaceCategories() {
        val selectedCategories = ChoosePlaceFragment.selectedCategories
        val quickPlaceCategoryAdapter =
            binding.quickPlaceCategory.rvCategories.adapter as? QuickPlaceCategoryAdapter

        quickPlaceCategoryAdapter?.let {
            it.clearCategories() // Clear the existing categories

            // If no categories are selected, return to avoid replacing with empty
            if (selectedCategories.isEmpty()) {
                // Re-add default categories to maintain at least some categories
                val defaultCategories = mutableListOf(
                    QuickPlaceCategoryDataClass("Restaurant", R.drawable.resturant_icon),
                    QuickPlaceCategoryDataClass("Bank", R.drawable.bank_icon),
                    QuickPlaceCategoryDataClass("Groceries", R.drawable.groceries_icon)
                )
                defaultCategories.forEach { category ->
                    it.addCategory(category) // Re-add default categories
                }
            } else {
                // Add selected categories
                selectedCategories.forEach { chooseCategory ->
                    // Assuming chooseCategory is of type ChoosePlaceCategory
                    val quickCategory = QuickPlaceCategoryDataClass(chooseCategory.title, chooseCategory.icon)
                    it.addCategory(quickCategory) // Add user-selected categories
                }
            }
            it.notifyDataSetChanged()
        }
    }

    // Setting up navigation for "Choose Place Category" to respective fragment
    private fun setupChoosePlaceCategory() {
        val quickPlaceCategoryLayout = binding.quickPlaceCategory
        val placeIcon =
            quickPlaceCategoryLayout.placeIcon // Access the profile icon from the included layout (layout_header)

        // Set up click listener for profile icon
        placeIcon.setOnClickListener {
            // Navigate to ProfileFragment
            val navController = findNavController()
            navController.navigate(R.id.action_homeFragment_to_choosePlaceFragment)
        }
    }

    // Set up place categories with individual RecyclerViews (horizontal)
    private fun setupPlaceCategories() {
        // Food & Drinks Category
        val foodAdapter = PlaceCategoryGroupAdapter(dineoutBestOffersList)
        binding.placeCategoryFoodDrinks.apply {
            setTitle("Food & Drinks")
            setDescription("Explore restaurants and cafes nearby")
            setRecyclerViewAdapter(foodAdapter)
        }
        binding.placeCategoryFoodDrinks.setSeeAllClickListener(findNavController())

        // Shopping Category
        val shoppingAdapter = PlaceCategoryGroupAdapter(dineoutBestOffersList)
        binding.placeCategoryShopping.apply {
            setTitle("Shopping")
            setDescription("Find the best places to shop")
            setRecyclerViewAdapter(shoppingAdapter)
        }
        binding.placeCategoryShopping.setSeeAllClickListener(findNavController())

        // Transportation Category
        val transportationAdapter = PlaceCategoryGroupAdapter(dineoutBestOffersList)
        binding.placeCategoryTransportation.apply {
            setTitle("Transportation")
            setDescription("Discover transport services around you")
            setRecyclerViewAdapter(transportationAdapter)
        }
        binding.placeCategoryTransportation.setSeeAllClickListener(findNavController())

        // Health Care Category
        val healthCareAdapter = PlaceCategoryGroupAdapter(dineoutBestOffersList)
        binding.placeCategoryHealthCare.apply {
            setTitle("Health Care")
            setDescription("Find clinics, hospitals, and healthcare services")
            setRecyclerViewAdapter(healthCareAdapter)
        }
        binding.placeCategoryHealthCare.setSeeAllClickListener(findNavController())

        // Financial Services Category
        val financialServicesAdapter = PlaceCategoryGroupAdapter(dineoutBestOffersList)
        binding.placeCategoryFinancialServices.apply {
            setTitle("Financial Services")
            setDescription("Locate banks and ATMs near you")
            setRecyclerViewAdapter(financialServicesAdapter)
        }
        binding.placeCategoryFinancialServices.setSeeAllClickListener(findNavController())

        // Public Services Category
        val publicServicesAdapter = PlaceCategoryGroupAdapter(dineoutBestOffersList)
        binding.placeCategoryPublicServices.apply {
            setTitle("Public Services")
            setDescription("Explore public service offices nearby")
            setRecyclerViewAdapter(publicServicesAdapter)
        }
        binding.placeCategoryPublicServices.setSeeAllClickListener(findNavController())

        // Fitness & Wellness Category
        val fitnessWellnessAdapter = PlaceCategoryGroupAdapter(dineoutBestOffersList)
        binding.placeCategoryFitnessWellness.apply {
            setTitle("Fitness & Wellness")
            setDescription("Discover gyms, spas, and wellness centers")
            setRecyclerViewAdapter(fitnessWellnessAdapter)
        }
        binding.placeCategoryFitnessWellness.setSeeAllClickListener(findNavController())

        // Personal Care Category
        val personalCareAdapter = PlaceCategoryGroupAdapter(dineoutBestOffersList)
        binding.placeCategoryPersonalCare.apply {
            setTitle("Personal Care")
            setDescription("Find beauty salons and personal care services")
            setRecyclerViewAdapter(personalCareAdapter)
        }
        binding.placeCategoryPersonalCare.setSeeAllClickListener(findNavController())

        // Entertainment Category
        val entertainmentAdapter = PlaceCategoryGroupAdapter(dineoutBestOffersList)
        binding.placeCategoryEntertainment.apply {
            setTitle("Entertainment")
            setDescription("Explore cinemas, parks, and entertainment spots")
            setRecyclerViewAdapter(entertainmentAdapter)
        }
        binding.placeCategoryEntertainment.setSeeAllClickListener(findNavController())

        // Education Category
        val educationAdapter = PlaceCategoryGroupAdapter(dineoutBestOffersList)
        binding.placeCategoryEducation.apply {
            setTitle("Education")
            setDescription("Locate schools, colleges, and educational centers")
            setRecyclerViewAdapter(educationAdapter)
        }
        binding.placeCategoryEducation.setSeeAllClickListener(findNavController())

        // Religious Category
        val religiousAdapter = PlaceCategoryGroupAdapter(dineoutBestOffersList)
        binding.placeCategoryReligious.apply {
            setTitle("Religious")
            setDescription("Find religious places nearby")
            setRecyclerViewAdapter(religiousAdapter)
        }
        binding.placeCategoryReligious.setSeeAllClickListener(findNavController())
    }
}
