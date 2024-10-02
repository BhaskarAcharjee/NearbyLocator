package com.example.nearbylocator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.model.PlaceItem
import com.example.nearbylocator.model.PlaceTypeIcon

class QuickPlaceCategoryAdapter(
    private val categories: MutableList<Any>, // Can be PlaceTypeIcon or PlaceItem.Header
    private val onItemClick: (PlaceTypeIcon) -> Unit // Still handles category clicks
) : RecyclerView.Adapter<QuickPlaceCategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.iv_category_icon)
        private val title: TextView = itemView.findViewById(R.id.tv_category_name)

        fun bind(item: Any) {
            when (item) {
                is PlaceTypeIcon -> {
                    icon.setImageResource(item.icon) // Set category icon
                    title.text = item.title // Set category title
                    itemView.setOnClickListener { onItemClick(item) }
                }

                is PlaceItem.Header -> {
                    icon.setImageResource(item.headerIcon) // Set header icon
                    title.text = item.title // Set header title
                }
            }
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

    fun clearCategories() {
        categories.clear()
        notifyDataSetChanged()
    }

    fun addCategory(item: Any) {
        categories.add(item)
        notifyItemInserted(categories.size - 1)
    }
}
