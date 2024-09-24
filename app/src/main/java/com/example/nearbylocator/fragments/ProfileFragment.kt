package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.nearbylocator.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        // Set up the toolbar
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)

        // Set the title for CollapsingToolbarLayout
        val collapsingToolbarLayout =
            rootView.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        collapsingToolbarLayout.title = ""

        val appBarLayout = rootView.findViewById<AppBarLayout>(R.id.app_bar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) == appBarLayout.totalScrollRange) {
                collapsingToolbarLayout.title = getString(R.string.profile_title)
            } else {
                collapsingToolbarLayout.title = ""
            }
        })

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Apply the status bar style for this fragment
        applyProfileThemeStatusBar()
    }

    private fun applyProfileThemeStatusBar() {
        // Change status bar color and apply ProfileTheme style
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.window?.statusBarColor =
                ContextCompat.getColor(requireContext(), R.color.profilePrimaryDark)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Revert the status bar color back to default when the fragment is destroyed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.window?.statusBarColor =
                ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark)
        }
    }
}
