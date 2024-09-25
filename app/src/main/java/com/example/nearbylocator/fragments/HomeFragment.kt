package com.example.nearbylocator.fragments

import QuickPlaceCategoryAdapter
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.DineoutHoriImageAdapter
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
    private lateinit var textSwitcher: TextSwitcher
    private var currentHintIndex = 0

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
        setupViewPager() // Set up the image slider
        setupPlaceCategories() // Set up multiple place categories like Food & Drinks, Shopping, etc.
        setupTextSwitcher() // Set up the text switcher for hints
        setupProfileIconNavigation() // Set up profile icon click event
        setupQuickCategoryNavigation() // Merge into one function
        setupChoosePlaceCategory()
        // Update quick categories when the fragment is created
        updateQuickPlaceCategories()
    }

    private fun updateQuickPlaceCategories() {
        val selectedCategories =
            ChoosePlaceFragment.selectedCategories // This should be of type List<ChoosePlaceCategory>
        val quickPlaceCategoryAdapter =
            binding.quickPlaceCategory.rvCategories.adapter as? QuickPlaceCategoryAdapter

        quickPlaceCategoryAdapter?.let {
            it.clearCategories()
            selectedCategories.forEach { chooseCategory -> // Assuming chooseCategory is of type ChoosePlaceCategory
                // Map ChoosePlaceCategory to QuickPlaceCategory
                val quickCategory = QuickPlaceCategoryDataClass(chooseCategory.title, chooseCategory.icon)
                it.addCategory(quickCategory)
            }
            it.notifyDataSetChanged()
        }
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

    // Set up place categories with individual RecyclerViews (horizontal)
    private fun setupPlaceCategories() {
        // Food & Drinks Category
        val foodAdapter = DineoutHoriImageAdapter(dineoutBestOffersList)
        binding.placeCategoryFoodDrinks.apply {
            setTitle("Food & Drinks")
            setDescription("Explore restaurants and cafes nearby")
            setRecyclerViewAdapter(foodAdapter)
        }
        binding.placeCategoryFoodDrinks.setSeeAllClickListener(findNavController())

        // Shopping Category
        val shoppingAdapter = DineoutHoriImageAdapter(dineoutBestOffersList)
        binding.placeCategoryShopping.apply {
            setTitle("Shopping")
            setDescription("Find the best places to shop")
            setRecyclerViewAdapter(shoppingAdapter)
        }
        binding.placeCategoryShopping.setSeeAllClickListener(findNavController())

        // Add more categories as needed using a similar pattern
    }

    // Set up a TextSwitcher for showing place hints that switch automatically
    private fun setupTextSwitcher() {
        val placeSearchbarLayout = binding.placeSearchbar
        textSwitcher = placeSearchbarLayout.textSwitcher
        textSwitcher.setFactory {
            val textView = TextView(context)
            textView.textSize = 16f
            textView.typeface =
                ResourcesCompat.getFont(requireContext(), R.font.swiggy_font_regular)
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
            textView
        }

        switchText() // Start switching text hints
    }

    // Recursively switch between place hint strings
    private fun switchText() {
        textSwitcher.setText(places_hint_Strings[currentHintIndex])
        currentHintIndex = (currentHintIndex + 1) % places_hint_Strings.size

        // Post delay to change the hint every 1.5 seconds
        textSwitcher.postDelayed(
            { switchText() },
            1500
        )
    }

    // Handle profile icon click navigation to the profile fragment
    private fun setupProfileIconNavigation() {
        val headerLayout = binding.rlHeader
        val profileIcon =
            headerLayout.profileIcon // Access the profile icon from the included layout (layout_header)

        // Set up click listener for profile icon
        profileIcon.setOnClickListener {
            // Navigate to ProfileFragment
            val navController = findNavController()
            navController.navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }

    // Merged Quick Category Navigation setup
    private fun setupQuickCategoryNavigation() {
        // Define category data
        val categories = mutableListOf( // Change to mutableListOf
            QuickPlaceCategoryDataClass("Restaurant", R.drawable.resturant_icon),
            QuickPlaceCategoryDataClass("Bank", R.drawable.bank_icon),
            QuickPlaceCategoryDataClass("ATM", R.drawable.atm_icon),
            QuickPlaceCategoryDataClass("Hospital", R.drawable.hospital_icon),
            QuickPlaceCategoryDataClass("Groceries", R.drawable.groceries_icon),
            QuickPlaceCategoryDataClass("Parking", R.drawable.parking_icon)
        )

        val quickPlaceCategoryAdapter = QuickPlaceCategoryAdapter(categories) { category ->
            // Handle the click on a category, you can navigate to a new fragment based on category
            when (category.title) {
                "Restaurant" -> findNavController().navigate(R.id.action_homeFragment_to_seeallFragment)
                "Bank" -> findNavController().navigate(R.id.action_homeFragment_to_seeallFragment)
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

    // Extension function for converting sp to pixels
    private fun Int.spToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.scaledDensity).toInt()
    }
}
