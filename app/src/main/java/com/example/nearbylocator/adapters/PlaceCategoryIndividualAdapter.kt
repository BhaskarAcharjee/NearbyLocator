package com.example.nearbylocator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.model.PlaceCategoryGroupDataClass

class PlaceCategoryIndividualAdapter(private val placeCategoryIndividualList: List<PlaceCategoryGroupDataClass>) :
    RecyclerView.Adapter<PlaceCategoryIndividualAdapter.PlaceCategoryIndividualHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceCategoryIndividualAdapter.PlaceCategoryIndividualHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_place_category_individual, parent, false)
        return PlaceCategoryIndividualHolder(view)
    }

    override fun onBindViewHolder(
        holder: PlaceCategoryIndividualAdapter.PlaceCategoryIndividualHolder,
        position: Int
    ) {
        val data = placeCategoryIndividualList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return placeCategoryIndividualList.size
    }

    inner class PlaceCategoryIndividualHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPlaceImage: ImageView = itemView.findViewById(R.id.iv_place_image)
        private val tvPlaceName: TextView = itemView.findViewById(R.id.tv_place_name)
        private val tvPlaceRating: TextView = itemView.findViewById(R.id.tv_place_rating)
        private val tvPlaceLocation: TextView = itemView.findViewById(R.id.tv_place_location)
        private val tvPlaceDistance: TextView = itemView.findViewById(R.id.tv_place_distance)
        private val tvPlaceType: TextView = itemView.findViewById(R.id.tv_place_type)
        private val tvPlacePrice: TextView = itemView.findViewById(R.id.tv_place_price)
        fun bind(data: PlaceCategoryGroupDataClass) {
            ivPlaceImage.setImageResource(data.placeImage)
            tvPlaceName.text = data.placeName
            tvPlaceRating.text = data.placeRating
            tvPlaceLocation.text = data.placeLocation
            tvPlaceDistance.text = data.placeDistance
            tvPlaceType.text = data.placeType
            tvPlacePrice.text = data.placePrice
        }
    }

}