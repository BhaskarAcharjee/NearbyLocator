package com.example.nearbylocator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.model.PlaceCategoryGroupDataClass

class PlaceCategoryGroupAdapter(private val placeCategoryGroupImageList: List<PlaceCategoryGroupDataClass>) :
    RecyclerView.Adapter<PlaceCategoryGroupAdapter.PlaceCategoryGroupImageHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceCategoryGroupAdapter.PlaceCategoryGroupImageHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_place_category_group, parent, false)
        return PlaceCategoryGroupImageHolder(view)
    }

    override fun onBindViewHolder(
        holder: PlaceCategoryGroupAdapter.PlaceCategoryGroupImageHolder,
        position: Int
    ) {
        val data = placeCategoryGroupImageList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return placeCategoryGroupImageList.size
    }

    inner class PlaceCategoryGroupImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivHotelImage: ImageView = itemView.findViewById(R.id.iv_hotel_image)
        private val tvHotelName: TextView = itemView.findViewById(R.id.tv_hotel_name)
        private val tvHotelRating: TextView = itemView.findViewById(R.id.tv_hotel_rating)
        private val tvHotelLocation: TextView = itemView.findViewById(R.id.tv_hotel_location)
        private val tvHotelDistance: TextView = itemView.findViewById(R.id.tv_hotel_distance)
        private val tvHotelType: TextView = itemView.findViewById(R.id.tv_hotel_type)
        private val tvHotelPrice: TextView = itemView.findViewById(R.id.tv_hotel_price)
        fun bind(data: PlaceCategoryGroupDataClass) {
            ivHotelImage.setImageResource(data.hotelImage)
            tvHotelName.text = data.hotelName
            tvHotelRating.text = data.hotelRating
            tvHotelLocation.text = data.hotelLocation
            tvHotelDistance.text = data.hotelDistance
            tvHotelType.text = data.hotelType
            tvHotelPrice.text = data.hotelPrice
        }
    }

}