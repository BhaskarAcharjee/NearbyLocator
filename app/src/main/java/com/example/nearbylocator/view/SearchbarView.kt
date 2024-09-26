package com.example.nearbylocator.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.nearbylocator.R
import com.google.android.material.card.MaterialCardView

class SearchBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var textSwitcher: TextSwitcher
    private var currentHintIndex = 0
    private val handler = Handler(Looper.getMainLooper())

    // Now using a mutable list for hints
    private val hintStrings = mutableListOf<String>()

    init {
        LayoutInflater.from(context).inflate(R.layout.view_searchbar, this, true)

        textSwitcher = findViewById(R.id.textSwitcher)

        setupTextSwitcher()
        switchText() // Start switching hints
    }

    // Set up the TextSwitcher
    private fun setupTextSwitcher() {
        textSwitcher.setFactory {
            val textView = TextView(context)
            textView.textSize = 16f
            textView.typeface = ResourcesCompat.getFont(context, R.font.swiggy_font_regular)
            textView.setTextColor(ContextCompat.getColor(context, R.color.grey))
            textView
        }
    }

    // Recursively switch between hint strings
    private fun switchText() {
        if (hintStrings.isNotEmpty()) {
            textSwitcher.setText(hintStrings[currentHintIndex])
            currentHintIndex = (currentHintIndex + 1) % hintStrings.size
            handler.postDelayed({ switchText() }, 1500) // Change hint every 1.5 seconds
        }
    }

    // Optional: Method to update the hints dynamically
    fun setHints(hints: Array<String>) {
        if (hints.isNotEmpty()) {
            handler.removeCallbacksAndMessages(null)
            hintStrings.clear()  // Clear the existing hints
            hintStrings.addAll(hints)  // Add the new hints
            currentHintIndex = 0
            switchText()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler.removeCallbacksAndMessages(null) // Clean up handler when view is detached
    }
}

