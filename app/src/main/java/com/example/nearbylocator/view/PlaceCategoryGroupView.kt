package com.example.nearbylocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.PlaceCategoryGroupAdapter
import com.example.nearbylocator.databinding.ViewPlaceCategoryGroupBinding
import com.example.nearbylocator.fragments.HomeFragmentDirections

class PlaceCategoryGroupView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: ViewPlaceCategoryGroupBinding =
        ViewPlaceCategoryGroupBinding.inflate(LayoutInflater.from(context), this, true)

    // Set the title of the category
    fun setTitle(title: String) {
        binding.tvCategoryTitle.text = title
    }

    // Set the description of the category
    fun setDescription(description: String) {
        binding.tvCategoryDescription.text = description
    }

    // Set the adapter for the RecyclerView
    fun setRecyclerViewAdapter(adapter: PlaceCategoryGroupAdapter) {
        binding.rvCategoryItems.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategoryItems.adapter = adapter
    }

    // Handle the "See All" click and navigate to MapviewFragment
    fun setSeeAllClickListener(navController: NavController, categoryTitle: String) {
        binding.tvSeeAll.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCategoryGroupFragment(categoryTitle)
            navController.navigate(action)
        }
    }


}
