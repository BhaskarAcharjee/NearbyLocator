package com.example.nearbylocator.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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
    private var editText: EditText
    private var currentHintIndex = 0
    private val handler = Handler(Looper.getMainLooper())

    private val hintStrings = mutableListOf<String>()

    init {
        LayoutInflater.from(context).inflate(R.layout.view_searchbar, this, true)

        textSwitcher = findViewById(R.id.textSwitcher)
        editText = findViewById(R.id.et_search_input)

        setupTextSwitcher()
        switchText() // Start switching hints

        // Set click listener to show keyboard and focus on EditText when the search bar is clicked
        this.setOnClickListener {
            showKeyboardAndFocus()
        }

        // Hide TextSwitcher and show EditText when it gains focus
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                textSwitcher.visibility = View.GONE
                editText.visibility = View.VISIBLE
            } else {
                // Show TextSwitcher if EditText is empty and it loses focus
                if (editText.text.isNullOrEmpty()) {
                    textSwitcher.visibility = View.VISIBLE
                    editText.visibility = View.GONE
                }
            }
        }
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

    // Show the keyboard and request focus for EditText
    private fun showKeyboardAndFocus() {
        editText.visibility = View.VISIBLE
        editText.requestFocus() // Request focus to the EditText

        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    // Method to update the hints dynamically
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
