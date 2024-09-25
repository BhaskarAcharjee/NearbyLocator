package com.example.nearbylocator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.model.ChoosePlaceCategoryDataClass

class ChoosePlaceCategoryAdapter(
    private val categories: List<ChoosePlaceCategoryDataClass>,
    private val onItemClick: (ChoosePlaceCategoryDataClass) -> Unit
) : RecyclerView.Adapter<ChoosePlaceCategoryAdapter.CategoryViewHolder>() {

    private val selectedCategories = mutableSetOf<ChoosePlaceCategoryDataClass>()

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.categoryIcon)
        private val name: TextView = itemView.findViewById(R.id.categoryName)

        fun bind(category: ChoosePlaceCategoryDataClass) {
            icon.setImageResource(category.icon)
            name.text = category.title

            // Handle the background change based on selection state
            if (selectedCategories.contains(category)) {
                icon.setBackgroundResource(R.drawable.rounded_corner_active)
            } else {
                icon.setBackgroundResource(R.drawable.rounded_corner)
            }

            itemView.setOnClickListener {
                // Add or remove from selected categories based on current state
                if (selectedCategories.contains(category)) {
                    selectedCategories.remove(category)
                    icon.setBackgroundResource(R.drawable.rounded_corner)
                } else {
                    if (selectedCategories.size < 5) {
                        selectedCategories.add(category)
                        icon.setBackgroundResource(R.drawable.rounded_corner_active)
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
    fun getSelectedCategories(): Set<ChoosePlaceCategoryDataClass> {
        return selectedCategories
    }
}
