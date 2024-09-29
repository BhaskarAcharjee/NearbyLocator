package com.example.nearbylocator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.model.PlaceTypeIconDataClass

class ChoosePlaceCategoryAdapter(
    private val categories: List<PlaceTypeIconDataClass>,
    private val onItemClick: (PlaceTypeIconDataClass) -> Unit
) : RecyclerView.Adapter<ChoosePlaceCategoryAdapter.CategoryViewHolder>() {

    private val selectedCategories = mutableSetOf<PlaceTypeIconDataClass>()

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.categoryIcon)
        private val name: TextView = itemView.findViewById(R.id.categoryName)
        private val tickIcon: ImageView = itemView.findViewById(R.id.tickIcon)

        fun bind(category: PlaceTypeIconDataClass) {
            icon.setImageResource(category.icon)
            name.text = category.title

            // Handle background and tick icon visibility based on selection state
            if (selectedCategories.contains(category)) {
                icon.setBackgroundResource(R.drawable.rounded_corner_active)
                tickIcon.visibility = View.VISIBLE
                icon.alpha = 0.5f  // Apply slight opacity for blur effect
            } else {
                icon.setBackgroundResource(R.drawable.rounded_corner)
                tickIcon.visibility = View.GONE
                icon.alpha = 1.0f  // Reset opacity
            }

            itemView.setOnClickListener {
                if (selectedCategories.contains(category)) {
                    selectedCategories.remove(category)
                    tickIcon.visibility = View.GONE
                    icon.setBackgroundResource(R.drawable.rounded_corner)
                    icon.alpha = 1.0f  // Reset opacity
                } else {
                    if (selectedCategories.size < 5) {
                        selectedCategories.add(category)
                        tickIcon.visibility = View.VISIBLE
                        icon.setBackgroundResource(R.drawable.rounded_corner_active)
                        icon.alpha = 0.5f  // Apply blur effect
                    }
                }

                onItemClick(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_choose_place_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    // Public method to get selected categories
    fun getSelectedCategories(): Set<PlaceTypeIconDataClass> {
        return selectedCategories
    }
}
