package com.example.nearbylocator.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.R
import com.google.android.material.card.MaterialCardView

class SearchBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var textSwitcher: TextSwitcher
    private var editText: EditText
    private var searchIcon: ImageView
    private var suggestionsRecyclerView: RecyclerView
    private var currentHintIndex = 0
    private val handler = Handler(Looper.getMainLooper())
    private var onSearchListener: ((String) -> Unit)? = null

    private val hintStrings = mutableListOf<String>()

    init {
        LayoutInflater.from(context).inflate(R.layout.view_searchbar, this, true)

        textSwitcher = findViewById(R.id.textSwitcher)
        editText = findViewById(R.id.et_search_input)
        searchIcon = findViewById(R.id.iv_search)
        suggestionsRecyclerView = findViewById(R.id.autocompleteRecyclerView)

        setupTextSwitcher()
        switchText()

        // Set click listener to show keyboard and focus on EditText when the search bar is clicked
        this.setOnClickListener {
            showKeyboardAndFocus()
        }

        // Hide TextSwitcher and show EditText when it gains focus
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                textSwitcher.visibility = View.GONE
                findViewById<TextView>(R.id.tv_search).visibility = View.GONE
                editText.visibility = View.VISIBLE
            } else {
                if (editText.text.isNullOrEmpty()) {
                    textSwitcher.visibility = View.VISIBLE
                    findViewById<TextView>(R.id.tv_search).visibility = View.VISIBLE
                    editText.visibility = View.GONE
                }
            }
        }

        // Listener to trigger search when "Done" button is pressed on keyboard
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val query = editText.text.toString()
                if (query.isNotEmpty()) {
                    onSearchListener?.invoke(query)
                    hideSuggestions() // Hide suggestions
                }
                true
            } else {
                false
            }
        }

        // Listener to trigger search when the search icon is clicked
        searchIcon.setOnClickListener {
            val query = editText.text.toString()
            if (query.isNotEmpty()) {
                onSearchListener?.invoke(query)
                hideSuggestions() // Hide suggestions
            }
        }

        // Text change listener to show suggestions
        editText.addTextChangedListener {
            val query = it.toString()
            if (query.isNotEmpty()) {
                showSuggestions(query) // Display suggestions
            } else {
                hideSuggestions() // Hide suggestions if input is empty
            }
        }
    }

    private fun setupTextSwitcher() {
        textSwitcher.setFactory {
            val textView = TextView(context)
            textView.textSize = 16f
            textView.typeface = ResourcesCompat.getFont(context, R.font.swiggy_font_regular)
            textView.setTextColor(ContextCompat.getColor(context, R.color.grey))
            textView
        }
    }

    private fun switchText() {
        if (hintStrings.isNotEmpty()) {
            textSwitcher.setText(hintStrings[currentHintIndex])
            currentHintIndex = (currentHintIndex + 1) % hintStrings.size
            handler.postDelayed({ switchText() }, 1500)
        }
    }

    private fun showKeyboardAndFocus() {
        editText.visibility = View.VISIBLE
        editText.requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    fun setOnSearchListener(listener: (String) -> Unit) {
        onSearchListener = listener
    }

    fun setHints(hints: Array<String>) {
        if (hints.isNotEmpty()) {
            handler.removeCallbacksAndMessages(null)
            hintStrings.clear()
            hintStrings.addAll(hints)
            currentHintIndex = 0
            switchText()
        }
    }

    // Update the EditText value programmatically
    fun updateEditText(query: String) {
        editText.setText(query)
    }

    // Show suggestions based on user input
    private fun showSuggestions(query: String) {
        suggestionsRecyclerView.visibility = View.VISIBLE
    }

    private fun hideSuggestions() {
        suggestionsRecyclerView.visibility = View.GONE
    }

    fun setOnTextChangedListener(onTextChanged: (String) -> Unit) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onTextChanged(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler.removeCallbacksAndMessages(null)
    }
}
