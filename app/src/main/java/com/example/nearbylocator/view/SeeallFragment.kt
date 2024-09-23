package com.example.nearbylocator.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.ImageSlideAdapter
import com.example.nearbylocator.adapters.InstamartImageAdapter
import com.example.nearbylocator.databinding.FragmentSeeallBinding
import com.example.nearbylocator.utils.hotDealsList
import com.example.nearbylocator.utils.instamartSlide1
import com.example.nearbylocator.utils.instamartSlide2
import com.example.nearbylocator.utils.topPicksList
import kotlin.math.abs

class SeeallFragment : Fragment() {

    private lateinit var handler: Handler
    private lateinit var slideAdapter1: ImageSlideAdapter
    private lateinit var slideAdapter2: ImageSlideAdapter
    private lateinit var hotDealsAdapter: InstamartImageAdapter
    private lateinit var topPicksAdapter: InstamartImageAdapter
    private lateinit var imageList1: ArrayList<Int>
    private lateinit var imageList2: ArrayList<Int>
    private lateinit var viewPager1: ViewPager2
    private lateinit var viewPager2: ViewPager2
    private lateinit var binding: FragmentSeeallBinding

    private val hintStrings =
        arrayOf("Biscuits", "Fruits & Vegitables", "Toothpaste", "Consmetics", "Snacks & Drinks")
    private var currentHintIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeeallBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager1 = binding.viewpager1
        viewPager2 = binding.viewpager2

        handler = Handler(Looper.myLooper()!!)
        imageList1 = instamartSlide1
        imageList2 = instamartSlide2

        slideAdapter1 = ImageSlideAdapter(imageList1, viewPager1)
        slideAdapter2 = ImageSlideAdapter(imageList2, viewPager2)

        viewPager1.adapter = slideAdapter1
        viewPager2.adapter = slideAdapter2
        viewPager1.offscreenPageLimit = 3
        viewPager2.offscreenPageLimit = 3
        viewPager1.clipToPadding = false
        viewPager2.clipToPadding = false
        viewPager1.clipChildren = false
        viewPager2.clipChildren = false
        viewPager1.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        setUpTransformer()

        viewPager1.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2500)
            }
        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2500)
            }
        })

        binding.textSwitcher.setFactory {
            val textView = TextView(context)
            textView.textSize = 16f
            textView.typeface =
                ResourcesCompat.getFont(requireContext(), R.font.swiggy_font_regular)
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
            textView
        }

        switchText()

        binding.apply {
            rvHotdeals.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            hotDealsAdapter = InstamartImageAdapter(hotDealsList)
            rvHotdeals.adapter = hotDealsAdapter

            rvTopPicks.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            topPicksAdapter = InstamartImageAdapter(topPicksList)
            rvTopPicks.adapter = topPicksAdapter

            svInstamart.viewTreeObserver.addOnScrollChangedListener {
                val linearLayoutHeight = llBanner.height
                val scrollY = svInstamart.scrollY

                if (scrollY >= linearLayoutHeight) {
                    llHeader.visibility = View.VISIBLE
                } else {
                    llHeader.visibility = View.GONE
                }
            }
        }

    }

    private fun setUpTransformer() {
        val transfomer = CompositePageTransformer()
        transfomer.addTransformer(MarginPageTransformer(90))
        transfomer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
            page.scaleX = 0.85f + r * 0.4f
        }
        viewPager1.setPageTransformer(transfomer)
        viewPager2.setPageTransformer(transfomer)
    }

    private val runnable = Runnable {
        viewPager1.currentItem += 1
        viewPager2.currentItem += 1
    }

    private fun switchText() {
        binding.textSwitcher.setText(hintStrings[currentHintIndex])
        currentHintIndex = (currentHintIndex + 1) % hintStrings.size

        binding.textSwitcher.postDelayed(
            { switchText() },
            1500
        ) // Delay between text switches (2 seconds in this example)
    }
}