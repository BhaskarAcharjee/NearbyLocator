package com.example.nearbylocator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.model.PlaceTypeIcon

class QuickDiscoverCategoryAdapter(
    private val context: Context,
    private val categories: MutableList<PlaceTypeIcon>,
    private val onItemClick: ((PlaceTypeIcon) -> Unit)? = null // Add the click listener
) : RecyclerView.Adapter<QuickDiscoverCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryIcon: ImageView = itemView.findViewById(R.id.iv_category_icon)
        private val categoryName: TextView = itemView.findViewById(R.id.tv_category_name)

        fun bind(category: PlaceTypeIcon) {
            categoryIcon.setImageResource(category.icon)
            categoryName.text = category.title

            // Handle item click
            itemView.setOnClickListener {
                onItemClick?.invoke(category) // Trigger the callback
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_quick_place_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    fun clearCategories() {
        categories.clear()
        notifyDataSetChanged()
    }

    // Add a new category and notify the adapter
    fun addCategory(category: PlaceTypeIcon) {
        categories.add(category)
        notifyItemInserted(categories.size - 1)
    }
}

