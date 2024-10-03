package com.example.nearbylocator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.model.PlaceItem

class MapviewFilterHeaderAdapter(
    private var items: List<PlaceItem.Header>,
    private val onItemClick: (Int) -> Unit // Callback for item click
) : RecyclerView.Adapter<MapviewFilterHeaderAdapter.HeaderViewHolder>() {

    private var selectedPosition: Int = RecyclerView.NO_POSITION // Track selected position

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.place_icon)
        private val title: TextView = itemView.findViewById(R.id.place_title)

        fun bind(headerItem: PlaceItem.Header, isSelected: Boolean) {
            icon.setImageResource(headerItem.headerIcon)
            title.text = headerItem.title

            // Set background based on selection state
            itemView.background = if (isSelected) {
                itemView.context.getDrawable(R.drawable.rounded_corner_active)
            } else {
                itemView.context.getDrawable(R.drawable.rounded_corner)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filter_mapview, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
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
    fun updateData(newItems: List<PlaceItem.Header>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}
