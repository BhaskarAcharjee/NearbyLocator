package com.example.nearbylocator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.model.PlaceTypeIcon

class QuickPlaceCategoryAdapter(
    private val categories: MutableList<PlaceTypeIcon>, // Change to MutableList
    private val onItemClick: (PlaceTypeIcon) -> Unit
) : RecyclerView.Adapter<QuickPlaceCategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.iv_category_icon)
        private val title: TextView = itemView.findViewById(R.id.tv_category_name)

        fun bind(category: PlaceTypeIcon) {
            icon.setImageResource(category.icon)
            title.text = category.title
            itemView.setOnClickListener { onItemClick(category) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_quick_place_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    // Clear the current categories and notify the adapter
    fun clearCategories() {
        categories.clear()
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }

    // Add a new category and notify the adapter
    fun addCategory(category: PlaceTypeIcon) {
        categories.add(category)
        notifyItemInserted(categories.size - 1) // Notify that a new item was inserted
    }
}
