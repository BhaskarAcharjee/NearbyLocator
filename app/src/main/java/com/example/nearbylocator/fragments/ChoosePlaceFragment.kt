package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.ChoosePlaceCategoryAdapter
import com.example.nearbylocator.databinding.FragmentChoosePlaceBinding
import com.example.nearbylocator.utils.choosePlaceCategories

class ChoosePlaceFragment : Fragment() {

    private lateinit var binding: FragmentChoosePlaceBinding
    private lateinit var adapter: ChoosePlaceCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoosePlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCategoryGrid()

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_choosePlaceFragment_to_homeFragment)
        }

        binding.doneButton.setOnClickListener {
            val selectedCategories = adapter.getSelectedCategories()
            if (selectedCategories.isNotEmpty()) {
                // Handle selected categories (e.g., pass data to another fragment or activity)
                Toast.makeText(
                    requireContext(),
                    "Selected categories: ${selectedCategories.size}",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(requireContext(), "No categories selected.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setupCategoryGrid() {
        // Initialize adapter with categories and click listener
        adapter = ChoosePlaceCategoryAdapter(choosePlaceCategories) { category ->
            // Handle click events if necessary (e.g., show a message)
            Toast.makeText(requireContext(), "${category.name} clicked!", Toast.LENGTH_SHORT).show()
        }

        // Set up RecyclerView with GridLayoutManager
        binding.categoryRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3) // 3 columns
            adapter = this@ChoosePlaceFragment.adapter
        }
    }
}
