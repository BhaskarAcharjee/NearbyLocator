package com.example.nearbylocator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.model.HoriServicesDataClass
import com.example.nearbylocator.R

class HoriImageAdapter(private val horiImageList: List<HoriServicesDataClass>) :
    RecyclerView.Adapter<HoriImageAdapter.HoriImageHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HoriImageAdapter.HoriImageHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_service_hori, parent, false)
        return HoriImageHolder(view)
    }

    override fun onBindViewHolder(holder: HoriImageAdapter.HoriImageHolder, position: Int) {
        val data = horiImageList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return horiImageList.size
    }

    inner class HoriImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivFood: ImageView = itemView.findViewById(R.id.iv_food)
        private val tvHotelName: TextView = itemView.findViewById(R.id.tv_place_name)
        private val tvRating: TextView = itemView.findViewById(R.id.tv_rating)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_time)
        private val tvType: TextView = itemView.findViewById(R.id.tv_type)
        fun bind(data: HoriServicesDataClass) {
            ivFood.setImageResource(data.imageName)
            tvHotelName.text = data.hotelName
            tvRating.text = data.foodRating
            tvTime.text = data.deliveryTime
            tvType.text = data.foodType
        }
    }

}