package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.utils.mapviewFavDataClasses
import com.example.nearbylocator.view.MapviewFavAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MapViewFragment : Fragment() {

    private lateinit var mapWebView: WebView
    private lateinit var recyclerView: RecyclerView
    private lateinit var mapviewFavAdapter: MapviewFavAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    // Views to hide and show
    private lateinit var searchBar: View
    private lateinit var favoriteCards: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mapview, container, false)

        mapWebView = view.findViewById(R.id.mapWebView)
        recyclerView = view.findViewById(R.id.recyclerView)
        searchBar = view.findViewById(R.id.customSearchBar)
        favoriteCards = view.findViewById(R.id.recyclerView)

        // Set up WebView
        setupWebView()

        // Set up RecyclerView
        setupRecyclerView()

        // Initialize BottomSheetBehavior
        setupBottomSheet(view)

        return view
    }

    private fun setupWebView() {
        mapWebView.webViewClient = WebViewClient()
        val webSettings: WebSettings = mapWebView.settings
        webSettings.javaScriptEnabled = true

        // Load the Google Maps iframe
        mapWebView.loadDataWithBaseURL(
            null,
            """
            <html>
            <body style='margin:0;padding:0;'>
            <iframe width='100%' height='100%' frameborder='0' scrolling='no' marginheight='0' marginwidth='0'
                src='https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q=1%20Grafton%20Street,%20Dublin,%20Ireland+(My%20Business%20Name)&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed'>
            </iframe>
            </body>
            </html>
            """.trimIndent(),
            "text/html",
            "UTF-8",
            null
        )
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // Pass a lambda that handles the click, expanding the bottom sheet
        mapviewFavAdapter = MapviewFavAdapter(mapviewFavDataClasses) { position ->
            // Handle the card click event here, expand the bottom sheet
            expandBottomSheet()
        }
        recyclerView.adapter = mapviewFavAdapter
    }

    private fun setupBottomSheet(view: View) {
        val bottomSheet: LinearLayout = view.findViewById(R.id.layoutMiscellaneous)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        // Initially hide the bottom sheet
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        // Set the peek height for the bottom sheet
        bottomSheetBehavior.peekHeight = 0 // Set to 0 to make it hidden initially

        // Handle bottom sheet state changes
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        // Hide the search bar and favorite cards when expanded
                        searchBar.visibility = View.GONE
                        favoriteCards.visibility = View.GONE
                    }

                    BottomSheetBehavior.STATE_COLLAPSED, BottomSheetBehavior.STATE_HIDDEN -> {
                        // Show the search bar and favorite cards when collapsed or hidden
                        searchBar.visibility = View.VISIBLE
                        favoriteCards.visibility = View.VISIBLE
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Optionally handle slide changes if needed
            }
        })
    }

    private fun expandBottomSheet() {
        // Expand the bottom sheet when a card is clicked
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}
