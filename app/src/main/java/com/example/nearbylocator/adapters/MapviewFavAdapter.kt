package com.example.nearbylocator.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.example.nearbylocator.model.MapviewFavDataClass

// Adding a click listener as a lambda function in the constructor
class MapviewFavAdapter(
    private val mapviewFavDataClasses: List<MapviewFavDataClass>,
    private val onCardClick: (Int) -> Unit // Lambda function to handle clicks
) : RecyclerView.Adapter<MapviewFavAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        // Inflate the card_mapview_fav.xml layout
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_mapview_fav, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val place = mapviewFavDataClasses[position]

        // Set data into views from the place object
        holder.tvHotelName.text = place.name
        holder.tvRating.text = place.rating.toString()
        holder.tvTime.text = place.time
        holder.tvType.text = place.type
        holder.tvHotelLocation.text = place.location
        holder.ivFood.setImageResource(place.imageResource) // Assuming place has an image resource
//        holder.ivOffer.setImageResource(R.drawable.img_extra10) // Assuming a default offer image

        // Handle the card click
        holder.itemView.setOnClickListener {
            onCardClick(position) // Call the lambda function, passing the clicked position
        }
    }

    override fun getItemCount(): Int {
        return mapviewFavDataClasses.size
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivFood: ImageView = itemView.findViewById(R.id.iv_food)
        val tvHotelName: TextView = itemView.findViewById(R.id.tv_place_name)
        val tvRating: TextView = itemView.findViewById(R.id.tv_rating)
        val tvTime: TextView = itemView.findViewById(R.id.tv_time)
        val tvType: TextView = itemView.findViewById(R.id.tv_type)
        val tvHotelLocation: TextView = itemView.findViewById(R.id.tv_place_location)
//        val ivOffer: ImageView = itemView.findViewById(R.id.iv_offer)
    }
}
