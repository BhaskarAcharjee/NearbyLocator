package com.example.nearbylocator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.model.PlaceTypeIcon

class MapviewFilterPlaceCategoryAdapter(private var items: List<PlaceTypeIcon>) : RecyclerView.Adapter<MapviewFilterPlaceCategoryAdapter.PlaceViewHolder>() {

    class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.place_icon)
        private val title: TextView = itemView.findViewById(R.id.place_title)

        fun bind(placeTypeIcon: PlaceTypeIcon) {
            icon.setImageResource(placeTypeIcon.icon)
            title.text = placeTypeIcon.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filter_mapview, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    // Method to update data
    fun updateData(newItems: List<PlaceTypeIcon>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}
