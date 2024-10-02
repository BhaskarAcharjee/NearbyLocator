package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.PlaceCategoryGroupAdapter
import com.example.nearbylocator.databinding.FragmentHomeBinding
import com.example.nearbylocator.model.PlaceItem
import com.example.nearbylocator.model.PlaceTypeIcon
import com.example.nearbylocator.repository.LocationRepository
import com.example.nearbylocator.constants.PlaceCategoryItems
import com.example.nearbylocator.utils.educationList
import com.example.nearbylocator.utils.entertainmentList
import com.example.nearbylocator.utils.financialServicesList
import com.example.nearbylocator.utils.fitnessWellnessList
import com.example.nearbylocator.utils.foodAndDrinksList
import com.example.nearbylocator.utils.healthCareList
import com.example.nearbylocator.utils.homeOfferImages
import com.example.nearbylocator.utils.personalCareList
import com.example.nearbylocator.utils.places_hint_Strings
import com.example.nearbylocator.utils.publicServicesList
import com.example.nearbylocator.utils.religiousList
import com.example.nearbylocator.utils.shoppingList
import com.example.nearbylocator.utils.transportationList

class HomeFragment : Fragment() {

    // Declare variables for views, adapters, and handlers
    private lateinit var binding: FragmentHomeBinding
    private lateinit var locationRepository: LocationRepository

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

        // Call setup methods for different sections
        setupHeaderView()
        setupSearchbarView()
        setupQuickCategoryNavigation()
        updateQuickPlaceCategories() // Update quick categories when the fragment is created
        setupPlaceCategories() // Set up multiple place categories like Food & Drinks, Shopping, etc.
        setupOffersView()   // Set up the OffersView with the image list
        updateDiscoverPlaceCategories() // Update quick categories when the fragment is created
    }

    // Set up the OffersView with the image list
    private fun setupOffersView() {
        val offersView = binding.offersView
        offersView.setImageList(homeOfferImages)  // Pass the image list to the OffersView
    }

    private fun setupHeaderView() {
        locationRepository = LocationRepository(requireContext())   // Initialize LocationRepository
        val headerView = binding.headerView     // Get reference to HeaderView
        headerView.observeLocationUpdates(locationRepository)   // Observe location updates to update header views
        locationRepository.startLocationUpdates()   // Start requesting location updates
        headerView.setupProfileIconNavigation(R.id.action_homeFragment_to_profileFragment)   // Handle navigation for profile icon
    }

    private fun setupSearchbarView() {
        val searchBarView = binding.searchBarView   // Access custom SearchBarView
        searchBarView.setHints(places_hint_Strings) // Update hints dynamically
    }

    override fun onDestroyView() {
        super.onDestroyView()
        locationRepository.stopLocationUpdates()    // Stop location updates when the fragment is destroyed
    }


    // Set up the custom QuickPlaceCategoryView
    private fun setupQuickCategoryNavigation() {
        val quickPlaceCategoryView = binding.quickPlaceCategoryView

        // Handle "See All" click event to navigate to ChoosePlaceFragment
        quickPlaceCategoryView.onSeeAllClicked = {
            findNavController().navigate(R.id.action_homeFragment_to_choosePlaceFragment)
        }
    }

    private fun updateQuickPlaceCategories() {
        // Extract selected categories from ChoosePlaceFragment
        val selectedCategories = ChoosePlaceFragment.selectedCategories

        // Map to unique headers
        val selectedHeaders = selectedCategories
            .mapNotNull { category -> getCategoryHeaderForCategory(category) }  // Convert categories to headers
            .toSet()  // Ensure uniqueness

        // Use the custom view to update headers
        binding.quickPlaceCategoryView.updateHeaders(selectedHeaders)
    }

    private fun getCategoryHeaderForCategory(category: PlaceTypeIcon): PlaceItem.Header? {
        // Find and return the header associated with the selected category
        val allCategories =
            PlaceCategoryItems.getPlaceCategories()  // Fetch all categories and headers
        for (item in allCategories) {
            if (item is PlaceItem.CategoryItem && item.place == category) {
                return item.header  // Return the header for this category
            }
        }
        return null
    }


    private fun updateDiscoverPlaceCategories() {
        val selectedCategories = ChoosePlaceFragment.selectedCategories

        // Use the custom view to update categories
        binding.quickDiscoverCategoryView.updateCategories(selectedCategories)
    }

    // ---------------------- Set up place categories with individual RecyclerViews (horizontal) ----------------------
    private fun setupPlaceCategories() {
        // Food & Drinks Category
        val foodAdapter = PlaceCategoryGroupAdapter(foodAndDrinksList)
        binding.placeCategoryFoodDrinks.apply {
            setTitle("Food & Drinks")
            setDescription("Explore restaurants and cafes nearby")
            setRecyclerViewAdapter(foodAdapter)
        }
        binding.placeCategoryFoodDrinks.setSeeAllClickListener(findNavController(), "Food & Drinks")

        // Shopping Category
        val shoppingAdapter = PlaceCategoryGroupAdapter(shoppingList)
        binding.placeCategoryShopping.apply {
            setTitle("Shopping")
            setDescription("Find the best places to shop")
            setRecyclerViewAdapter(shoppingAdapter)
        }
        binding.placeCategoryShopping.setSeeAllClickListener(findNavController(), "Shopping")

        // Transportation Category
        val transportationAdapter = PlaceCategoryGroupAdapter(transportationList)
        binding.placeCategoryTransportation.apply {
            setTitle("Transportation")
            setDescription("Discover transport services around you")
            setRecyclerViewAdapter(transportationAdapter)
        }
        binding.placeCategoryTransportation.setSeeAllClickListener(
            findNavController(),
            "Transportation"
        )

        // Health Care Category
        val healthCareAdapter = PlaceCategoryGroupAdapter(healthCareList)
        binding.placeCategoryHealthCare.apply {
            setTitle("Health Care")
            setDescription("Find clinics, hospitals, and healthcare services")
            setRecyclerViewAdapter(healthCareAdapter)
        }
        binding.placeCategoryHealthCare.setSeeAllClickListener(findNavController(), "Health Care")

        // Financial Services Category
        val financialServicesAdapter = PlaceCategoryGroupAdapter(financialServicesList)
        binding.placeCategoryFinancialServices.apply {
            setTitle("Financial Services")
            setDescription("Locate banks and ATMs near you")
            setRecyclerViewAdapter(financialServicesAdapter)
        }
        binding.placeCategoryFinancialServices.setSeeAllClickListener(
            findNavController(),
            "Financial Services"
        )

        // Public Services Category
        val publicServicesAdapter = PlaceCategoryGroupAdapter(publicServicesList)
        binding.placeCategoryPublicServices.apply {
            setTitle("Public Services")
            setDescription("Explore public service offices nearby")
            setRecyclerViewAdapter(publicServicesAdapter)
        }
        binding.placeCategoryPublicServices.setSeeAllClickListener(
            findNavController(),
            "Public Services"
        )

        // Fitness & Wellness Category
        val fitnessWellnessAdapter = PlaceCategoryGroupAdapter(fitnessWellnessList)
        binding.placeCategoryFitnessWellness.apply {
            setTitle("Fitness & Wellness")
            setDescription("Discover gyms, spas, and wellness centers")
            setRecyclerViewAdapter(fitnessWellnessAdapter)
        }
        binding.placeCategoryFitnessWellness.setSeeAllClickListener(
            findNavController(),
            "Fitness & Wellness"
        )

        // Personal Care Category
        val personalCareAdapter = PlaceCategoryGroupAdapter(personalCareList)
        binding.placeCategoryPersonalCare.apply {
            setTitle("Personal Care")
            setDescription("Find beauty salons and personal care services")
            setRecyclerViewAdapter(personalCareAdapter)
        }
        binding.placeCategoryPersonalCare.setSeeAllClickListener(
            findNavController(),
            "Personal Care"
        )

        // Entertainment Category
        val entertainmentAdapter = PlaceCategoryGroupAdapter(entertainmentList)
        binding.placeCategoryEntertainment.apply {
            setTitle("Entertainment")
            setDescription("Explore cinemas, parks, and entertainment spots")
            setRecyclerViewAdapter(entertainmentAdapter)
        }
        binding.placeCategoryEntertainment.setSeeAllClickListener(
            findNavController(),
            "Entertainment"
        )

        // Education Category
        val educationAdapter = PlaceCategoryGroupAdapter(educationList)
        binding.placeCategoryEducation.apply {
            setTitle("Education")
            setDescription("Locate schools, colleges, and educational centers")
            setRecyclerViewAdapter(educationAdapter)
        }
        binding.placeCategoryEducation.setSeeAllClickListener(findNavController(), "Education")

        // Religious Category
        val religiousAdapter = PlaceCategoryGroupAdapter(religiousList)
        binding.placeCategoryReligious.apply {
            setTitle("Religious")
            setDescription("Find religious places nearby")
            setRecyclerViewAdapter(religiousAdapter)
        }
        binding.placeCategoryReligious.setSeeAllClickListener(findNavController(), "Religious")
    }

}
