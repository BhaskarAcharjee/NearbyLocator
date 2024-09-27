package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nearbylocator.adapters.PlaceCategoryIndividualImageAdapter
import com.example.nearbylocator.databinding.FragmentCategoryIndividualBinding
import com.example.nearbylocator.utils.dineoutMoreList
import com.example.nearbylocator.utils.services_hint_Strings

class CategoryIndividualFragment : Fragment() {

    private lateinit var binding: FragmentCategoryIndividualBinding
    private lateinit var placeCategoryIndividualImageAdapter: PlaceCategoryIndividualImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryIndividualBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize necessary components
        setupSearchBar()
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() {
        binding.apply {
            // Setup vertical RecyclerView for more around you
            rvMorearoundyou.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            placeCategoryIndividualImageAdapter = PlaceCategoryIndividualImageAdapter(dineoutMoreList)
            rvMorearoundyou.adapter = placeCategoryIndividualImageAdapter

            svDineout.viewTreeObserver.addOnScrollChangedListener {
                val linearLayoutHeight = llSearchbar.height
                val scrollY = svDineout.scrollY

                if (scrollY >= linearLayoutHeight) {
                    llSearchbar.visibility = View.VISIBLE
                } else {
                    llSearchbar.visibility = View.GONE
                }
            }

        }
    }

    private fun setupSearchBar() {
        val searchBarView = binding.searchBarView
        searchBarView.setHints(services_hint_Strings)   // Sets up the search bar hints dynamically
    }

}

