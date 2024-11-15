package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nearbylocator.databinding.FragmentEventBinding
import com.example.nearbylocator.utils.places_hint_Strings

class EventFragment : Fragment() {

    private lateinit var binding: FragmentEventBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchbarView()

    }

    private fun setupSearchbarView() {
        val searchBarView = binding.searchBarView   // Access custom SearchBarView
        searchBarView.setHints(places_hint_Strings) // Update hints dynamically
    }

}
