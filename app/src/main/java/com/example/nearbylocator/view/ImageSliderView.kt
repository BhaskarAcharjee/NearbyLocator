package com.example.nearbylocator.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.ImageSlideAdapter
import kotlin.math.abs

class OffersView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var slideAdapter: ImageSlideAdapter

    init {
        LayoutInflater.from(context).inflate(R.layout.view_image_slider, this, true)
        setupViewPager()
    }

    // Initialize ViewPager2 and set its adapter and transformer
    private fun setupViewPager() {
        viewPager2 = findViewById(R.id.viewpager2)
        handler = Handler(Looper.myLooper()!!)
    }

    // Function to set the image list
    fun setImageList(images: ArrayList<Int>) {
        imageList = images
        slideAdapter = ImageSlideAdapter(imageList, viewPager2)
        viewPager2.adapter = slideAdapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        setUpTransformer()

        // Auto-scroll images
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2500)
            }
        })
    }

    // Set up transformer for page animations
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

    // Runnable for auto-scrolling
    private val runnable = Runnable {
        viewPager2.currentItem += 1
    }
}
