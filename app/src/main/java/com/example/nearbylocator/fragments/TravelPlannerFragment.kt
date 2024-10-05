package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.nearbylocator.R

class TravelPlannerFragment : Fragment() {

    private lateinit var questionContainer: LinearLayout
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button
    private lateinit var aiSuggestionText: TextView
    private lateinit var resultText: TextView
    private lateinit var progressBar: ProgressBar // Declare the progress bar

    private var currentStep = 1
    private var destination: String? = null
    private var numberOfDays: Int? = null
    private var budget: Int? = null
    private var features: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_travel_planner, container, false)

        questionContainer = view.findViewById(R.id.question_container)
        nextButton = view.findViewById(R.id.btn_next)
        previousButton = view.findViewById(R.id.btn_previous)
        aiSuggestionText = view.findViewById(R.id.ai_suggestion_text)
        resultText = view.findViewById(R.id.result_text)
        progressBar = view.findViewById(R.id.timeline_progress_bar) // Initialize the progress bar

        setupStep(currentStep)

        nextButton.setOnClickListener {
            currentStep++
            setupStep(currentStep)
        }

        previousButton.setOnClickListener {
            currentStep--
            setupStep(currentStep)
        }

        return view
    }

    private fun setupStep(step: Int) {
        questionContainer.removeAllViews()
        previousButton.visibility = if (step > 1) View.VISIBLE else View.GONE

        // Update the progress bar
        progressBar.progress = step

        when (step) {
            1 -> showDestinationInput()
            2 -> showNumberPicker()
            3 -> showBudgetInput()
            4 -> showFeaturesSelection()
            5 -> showResults()
            else -> return
        }
    }

    private fun showDestinationInput() {
        val editText = EditText(requireContext()).apply {
            hint = "Enter your destination"
        }
        questionContainer.addView(editText)
        editText.setOnEditorActionListener { _, _, _ ->
            destination = editText.text.toString()
            true
        }
    }

    private fun showNumberPicker() {
        val numberPicker = NumberPicker(requireContext()).apply {
            minValue = 1
            maxValue = 30
            wrapSelectorWheel = false
            setOnValueChangedListener { _, _, newValue ->
                numberOfDays = newValue
            }
        }
        questionContainer.addView(numberPicker)
    }

    private fun showBudgetInput() {
        val editText = EditText(requireContext()).apply {
            hint = "Enter your budget"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
        }
        questionContainer.addView(editText)
        editText.setOnEditorActionListener { _, _, _ ->
            budget = editText.text.toString().toIntOrNull()
            true
        }
    }

    private fun showFeaturesSelection() {
        val featuresOptions = listOf("Beach", "Adventure", "Cultural", "Relaxation")
        featuresOptions.forEach { feature ->
            val button = Button(requireContext()).apply {
                text = feature
                setOnClickListener {
                    if (features.contains(feature)) {
                        features.remove(feature)
                    } else {
                        features.add(feature)
                    }
                    updateFeatureButtons()
                }
            }
            questionContainer.addView(button)
        }
    }

    private fun updateFeatureButtons() {
        // Logic to visually indicate selected features
    }

    private fun showResults() {
        aiSuggestionText.visibility = View.VISIBLE
        resultText.visibility = View.VISIBLE
        resultText.text = "Your Travel Plan:\n" +
                "Destination: $destination\n" +
                "Days: $numberOfDays\n" +
                "Budget: $budget\n" +
                "Features: ${features.joinToString(", ")}"
    }
}
