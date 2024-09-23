package com.example.nearbylocator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R

class MapViewFragment : Fragment() {

    private lateinit var mapWebView: WebView
    private lateinit var recyclerView: RecyclerView
    private lateinit var cardAdapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mapview, container, false)
        mapWebView = view.findViewById(R.id.mapWebView)
        recyclerView = view.findViewById(R.id.recyclerView)

        // Set up WebView
        setupWebView()

        // Set up RecyclerView
        setupRecyclerView()

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

        // Sample data
        val places = listOf(
            Place(
                "Salem RR Briyani",
                4.5f,
                "20 mins",
                "Briyani, Pizza",
                "Medavakkam, 3.0 km",
                R.drawable.verti_img_1
            ),
            Place(
                "Burger King",
                4.0f,
                "15 mins",
                "Burgers, Fast Food",
                "Velachery, 2.5 km",
                R.drawable.verti_img_2
            ),
            Place(
                "Domino's Pizza",
                4.2f,
                "10 mins",
                "Pizza, Italian",
                "Tambaram, 4.0 km",
                R.drawable.verti_img_3
            ),
            Place(
                "Subway",
                3.8f,
                "25 mins",
                "Sandwiches, Salads",
                "Sholinganallur, 5.0 km",
                R.drawable.verti_img_4
            )
        )

        cardAdapter = CardAdapter(places)
        recyclerView.adapter = cardAdapter
    }
}
