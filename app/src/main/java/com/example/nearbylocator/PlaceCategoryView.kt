package com.example.nearbylocator.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nearbylocator.adapters.DineoutHoriImageAdapter
import com.example.nearbylocator.databinding.ViewPlaceCategoryBinding

class PlaceCategoryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: ViewPlaceCategoryBinding =
        ViewPlaceCategoryBinding.inflate(LayoutInflater.from(context), this, true)

    // Set the title of the category
    fun setTitle(title: String) {
        binding.tvCategoryTitle.text = title
    }

    // Set the description of the category
    fun setDescription(description: String) {
        binding.tvCategoryDescription.text = description
    }

    // Set the adapter for the RecyclerView
    fun setRecyclerViewAdapter(adapter: DineoutHoriImageAdapter) {
        binding.rvCategoryItems.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategoryItems.adapter = adapter
    }

    // Handle the "See All" click (Optional)
    fun setSeeAllClickListener(listener: OnClickListener) {
        binding.tvSeeAll.setOnClickListener(listener)
    }
}
