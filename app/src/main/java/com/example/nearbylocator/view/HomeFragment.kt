package com.example.nearbylocator.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.nearbylocator.adapters.ImageSlideAdapter
import com.example.nearbylocator.R
import com.example.nearbylocator.databinding.FragmentHomeBinding
import kotlin.math.abs


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var slideAdapter: ImageSlideAdapter

    private lateinit var textSwitcher: TextSwitcher
    private val hintStrings = arrayOf(
        "Restaurant",
        "Cafe",
        "Bar",
        "Grocery Store",
        "Supermarket",
        "Bank",
        "ATM",
        "Hospital",
        "Clinic",
        "Pharmacy",
        "Gas Station",
        "Salon",
        "Gym",
        "Park",
        "Movie Theater",
        "Shopping Mall",
        "Library",
        "Museum",
        "Post Office",
        "Hotel",
        "Parking",
        "Car Repair",
        "Laundry",
        "Bus Stop",
        "Train Station",
        "Airport",
        "Police Station",
        "School",
        "University",
        "Church",
        "Temple",
        "Mosque",
        "Zoo"
    )

    private var currentHintIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2500)
            }
        })

        textSwitcher = binding.textSwitcher
        textSwitcher.setFactory {
            val textView = TextView(context)
            textView.textSize = 16f
            textView.typeface =
                ResourcesCompat.getFont(requireContext(), R.font.swiggy_font_regular)
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
            textView
        }

        switchText()

        // Set up click listener for profile icon
        binding.profileIcon.setOnClickListener {
            // Navigate to ProfileFragment
            val navController = findNavController()
            navController.navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer() {
        val transfomer = CompositePageTransformer()
        transfomer.addTransformer(MarginPageTransformer(40))
        transfomer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
            page.scaleX = 0.85f + r * 0.3f
        }
        viewPager2.setPageTransformer(transfomer)
    }

    private fun switchText() {
        textSwitcher.setText(hintStrings[currentHintIndex])
        currentHintIndex = (currentHintIndex + 1) % hintStrings.size

        textSwitcher.postDelayed(
            { switchText() },
            1500
        ) // Delay between text switches (2 seconds in this example)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2500)
    }
}