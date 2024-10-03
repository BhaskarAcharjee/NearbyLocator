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

class ChoosePlaceCategoryAdapter(
    private val items: List<PlaceItem>,
    private val onItemClick: (PlaceTypeIcon) -> Unit,
    private val onMaxSelectionReached: (() -> Unit)? = null // Callback for max selection reached
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val selectedCategories = mutableSetOf<PlaceTypeIcon>()

    // Define view types
    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_CATEGORY = 1
    }

    // ViewHolder for Header
    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerText: TextView = itemView.findViewById(R.id.headerText)

        fun bind(title: String) {
            headerText.text = title
        }
    }

    // ViewHolder for CategoryItem
    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.categoryIcon)
        private val name: TextView = itemView.findViewById(R.id.categoryName)
        private val tickIcon: ImageView = itemView.findViewById(R.id.tickIcon)

        fun bind(category: PlaceTypeIcon) {
            icon.setImageResource(category.icon)
            name.text = category.title

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
                    if (selectedCategories.size < 12) {
                        selectedCategories.add(category)
                        tickIcon.visibility = View.VISIBLE
                        icon.setBackgroundResource(R.drawable.rounded_corner_active)
                        icon.alpha = 0.5f  // Apply blur effect
                    } else {
                        // Notify the fragment that the max selection limit has been reached
                        onMaxSelectionReached?.invoke()
                    }
                }

                onItemClick(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_header_choose_place_category, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_choose_place_category, parent, false)
            CategoryViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is PlaceItem.Header -> (holder as HeaderViewHolder).bind(item.title)
            is PlaceItem.CategoryItem -> (holder as CategoryViewHolder).bind(item.place)
        }
    }

    override fun getItemCount(): Int = items.size

    // Determine view type based on item
    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is PlaceItem.Header -> VIEW_TYPE_HEADER
            is PlaceItem.CategoryItem -> VIEW_TYPE_CATEGORY
        }
    }

    // Public method to get selected categories
    fun getSelectedCategories(): Set<PlaceTypeIcon> {
        return selectedCategories
    }
}
