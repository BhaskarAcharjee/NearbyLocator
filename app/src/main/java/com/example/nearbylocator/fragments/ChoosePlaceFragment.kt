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
import com.example.nearbylocator.model.PlaceTypeIconDataClass

class ChoosePlaceFragment : Fragment() {

    private lateinit var binding: FragmentChoosePlaceBinding
    private lateinit var adapter: ChoosePlaceCategoryAdapter

    // Shared variable to hold selected categories
    companion object {
        var selectedCategories: MutableSet<PlaceTypeIconDataClass> = mutableSetOf()
    }

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
            when {
                selectedCategories.size < 3 -> {
                    Toast.makeText(
                        requireContext(),
                        "Please select at least 3 categories.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                selectedCategories.size > 5 -> {
                    Toast.makeText(
                        requireContext(),
                        "You can select up to 5 categories.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    // Update the shared variable with selected categories
                    Companion.selectedCategories.clear()
                    Companion.selectedCategories.addAll(selectedCategories)
                    findNavController().navigate(R.id.action_choosePlaceFragment_to_homeFragment)
                }
            }
        }
    }

    private fun setupCategoryGrid() {
        adapter = ChoosePlaceCategoryAdapter(choosePlaceCategories) { category ->

        }

        binding.categoryRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = this@ChoosePlaceFragment.adapter
        }
    }
}
