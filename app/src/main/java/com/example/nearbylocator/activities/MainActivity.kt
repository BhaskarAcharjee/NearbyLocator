package com.example.nearbylocator.activities

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.nearbylocator.R
import com.example.nearbylocator.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            handleFragmentChange(destination.id)
        }
    }

    private fun handleFragmentChange(destinationId: Int) {
        val textViews = arrayOf(
            binding.tvHome, binding.tvService, binding.tvMapview, binding.tvEvent, binding.tvTravelPlanner
        )

        resetTextStyles(textViews)

        when (destinationId) {
            R.id.homeFragment -> {
                updateStatusBar(R.color.white, true)
                updateViewVisibility(binding.viewHome)
                updateSelectedTextView(binding.tvHome)
            }

            R.id.serviceFragment -> {
                updateStatusBar(R.color.white, true)
                updateViewVisibility(binding.viewService)
                updateSelectedTextView(binding.tvService)
            }

            R.id.mapviewFragment -> {
                updateStatusBar(R.color.white, false)
                updateViewVisibility(binding.viewMapview)
                updateSelectedTextView(binding.tvMapview)
            }

            R.id.eventFragment -> {
                updateStatusBar(R.color.white, true)
                updateViewVisibility(binding.viewEvent)
                updateSelectedTextView(binding.tvEvent)
            }

            R.id.travelPlannerFragment -> {
                updateStatusBar(R.color.white, true)
                updateViewVisibility(binding.viewTravelPlanner)
                updateSelectedTextView(binding.tvTravelPlanner)
            }

            R.id.categoryGroupFragment -> {
                updateStatusBar(R.color.pale_pink, true)
            }
            R.id.profileFragment -> {
                updateStatusBar(R.color.bg_violet, true)
            }

        }
    }

    @SuppressLint("NewApi")
    private fun updateStatusBar(colorResId: Int, isLightStatusBar: Boolean) {
        window.statusBarColor = getColor(colorResId)

        val nightModeFlags = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK

        when (nightModeFlags) {
            android.content.res.Configuration.UI_MODE_NIGHT_YES -> {
                // Dark mode: set icons to white (remove APPEARANCE_LIGHT_STATUS_BARS)
                window.decorView.windowInsetsController?.setSystemBarsAppearance(
                    0, APPEARANCE_LIGHT_STATUS_BARS
                )
            }
            android.content.res.Configuration.UI_MODE_NIGHT_NO -> {
                // Light mode: set icons to black (enable APPEARANCE_LIGHT_STATUS_BARS)
                if (isLightStatusBar) {
                    window.decorView.windowInsetsController?.setSystemBarsAppearance(
                        APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS
                    )
                } else {
                    window.decorView.windowInsetsController?.setSystemBarsAppearance(
                        0, APPEARANCE_LIGHT_STATUS_BARS
                    )
                }
            }
        }
    }

    private fun updateViewVisibility(visibleView: View) {
        // Hide all views
        binding.viewHome.visibility = View.GONE
        binding.viewService.visibility = View.GONE
        binding.viewMapview.visibility = View.GONE
        binding.viewEvent.visibility = View.GONE
        binding.viewTravelPlanner.visibility = View.GONE

        // Show only the selected view
        visibleView.visibility = View.VISIBLE
    }

    private fun resetTextStyles(textViews: Array<TextView>) {
        for (textView in textViews) {
            textView.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
            textView.typeface = Typeface.create(textView.typeface, Typeface.NORMAL)
        }
    }

    private fun updateSelectedTextView(selectedTextView: TextView) {
        selectedTextView.setTextColor(ContextCompat.getColor(this,R.color.blackTextColor))
        selectedTextView.typeface = Typeface.create(selectedTextView.typeface, Typeface.BOLD)
    }
}
