package com.example.swiggycloneapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swiggycloneapp.R
import com.example.swiggycloneapp.model.DineoutDataClass

class DineoutVertiImageAdapter(private val horiImageList: List<DineoutDataClass>) :
    RecyclerView.Adapter<DineoutVertiImageAdapter.DineoutVertiImageHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DineoutVertiImageAdapter.DineoutVertiImageHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.dineout_verti_card, parent, false)
        return DineoutVertiImageHolder(view)
    }

    override fun onBindViewHolder(
        holder: DineoutVertiImageAdapter.DineoutVertiImageHolder,
        position: Int
    ) {
        val data = horiImageList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return horiImageList.size
    }

    inner class DineoutVertiImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivFood: ImageView = itemView.findViewById(R.id.iv_hotel)
        private val tvHotelName: TextView = itemView.findViewById(R.id.tv_hotel_name)
        private val tvHotelRating: TextView = itemView.findViewById(R.id.tv_hotel_rating)
        private val tvHotelLocation: TextView = itemView.findViewById(R.id.tv_hotel_location)
        private val tvHotelDistance: TextView = itemView.findViewById(R.id.tv_hotel_distance)
        private val tvHotelType: TextView = itemView.findViewById(R.id.tv_hotel_type)
        private val tvHotelPrice: TextView = itemView.findViewById(R.id.tv_hotel_price)
        fun bind(data: DineoutDataClass) {
            ivFood.setImageResource(data.hotelImage)
            tvHotelName.text = data.hotelName
            tvHotelRating.text = data.hotelRating
            tvHotelLocation.text = data.hotelLocation
            tvHotelDistance.text = data.hotelDistance
            tvHotelType.text = data.hotelType
            tvHotelPrice.text = data.hotelPrice
        }
    }

}