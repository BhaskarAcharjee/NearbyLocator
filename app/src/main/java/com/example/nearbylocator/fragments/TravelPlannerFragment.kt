package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nearbylocator.databinding.FragmentTravelPlannerBinding

class TravelPlannerFragment : Fragment() {
    private lateinit var binding: FragmentTravelPlannerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTravelPlannerBinding.inflate(inflater, container, false)
        return binding.root
    }

}