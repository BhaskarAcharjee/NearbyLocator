package com.example.nearbylocator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.model.PlaceTypeIcon

class MapviewFilterPlaceCategoryAdapter(
    private var items: List<PlaceTypeIcon>,
    private val onItemClick: (Int) -> Unit // Callback for item click
) : RecyclerView.Adapter<MapviewFilterPlaceCategoryAdapter.PlaceViewHolder>() {

    private var selectedPosition: Int = RecyclerView.NO_POSITION // Track selected position

    class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.place_icon)
        private val title: TextView = itemView.findViewById(R.id.place_title)

        fun bind(placeTypeIcon: PlaceTypeIcon, isSelected: Boolean) {
            icon.setImageResource(placeTypeIcon.icon)
            title.text = placeTypeIcon.title

            // Set background based on selection state
            itemView.background = if (isSelected) {
                itemView.context.getDrawable(R.drawable.rounded_corner_active) // Use itemView.context
            } else {
                itemView.context.getDrawable(R.drawable.rounded_corner) // Use itemView.context
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filter_mapview, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val isSelected = position == selectedPosition // Determine if the item is selected
        holder.bind(items[position], isSelected)

        // Handle item clicks
        holder.itemView.setOnClickListener {
            // Update the selected position
            selectedPosition = position
            onItemClick(position) // Notify the click event
            notifyDataSetChanged() // Refresh the list to update backgrounds
        }
    }

    override fun getItemCount(): Int = items.size

    // Method to update data
    fun updateData(newItems: List<PlaceTypeIcon>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}
