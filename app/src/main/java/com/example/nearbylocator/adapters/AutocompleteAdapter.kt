package com.example.nearbylocator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.example.nearbylocator.R

class AutocompleteAdapter(
    private var predictions: List<AutocompletePrediction>,
    private val onPredictionClick: (AutocompletePrediction) -> Unit
) : RecyclerView.Adapter<AutocompleteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_autocomplete_prediction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prediction = predictions[position]
        holder.bind(prediction)
        holder.itemView.setOnClickListener {
            onPredictionClick(prediction)
        }
    }

    override fun getItemCount(): Int {
        return predictions.size
    }

    fun updateData(newPredictions: List<AutocompletePrediction>) {
        this.predictions = newPredictions
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val placeNameTextView: TextView = itemView.findViewById(R.id.place_prediction_text)

        fun bind(prediction: AutocompletePrediction) {
            placeNameTextView.text = prediction.getFullText(null)
        }
    }
}
