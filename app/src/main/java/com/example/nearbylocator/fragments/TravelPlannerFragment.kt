package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.nearbylocator.R
import com.example.nearbylocator.databinding.FragmentTravelPlannerBinding
import com.example.nearbylocator.utils.places_hint_Strings

class TravelPlannerFragment : Fragment() {
    private lateinit var binding: FragmentTravelPlannerBinding
    private val questions = mutableListOf<Question>()
    private var currentQuestionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTravelPlannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchbarView()
        setupQuestions() // Prepare the questions
        updateQuestionView() // Display the first question

        // Set up button click listeners
        binding.btnNext.setOnClickListener { onNextButtonClick() }
        binding.btnPrevious.setOnClickListener { onPreviousButtonClick() }
    }

    private fun setupSearchbarView() {
        val searchBarView = binding.searchBarView   // Access custom SearchBarView
        searchBarView.setHints(places_hint_Strings) // Update hints dynamically
    }

    private fun setupQuestions() {
        questions.add(Question("Where do you want to go?", true)) // Location question
        questions.add(Question("How many days?", false)) // Days question
        questions.add(Question("Choose a budget: Economic, Normal, Luxury", false)) // Budget question
        questions.add(Question("Choose features: Chill Mode, Nature, etc.", false)) // Features question
        questions.add(Question("AI Suggestions will be displayed here.", false, true)) // Suggestions
    }

    private fun updateQuestionView() {
        binding.questionContainer.removeAllViews() // Clear previous views

        val question = questions[currentQuestionIndex]

        if (question.isOpenEnded) {
            // For open-ended questions, show an EditText
            val editText = EditText(requireContext())
            editText.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            editText.hint = question.text
            binding.questionContainer.addView(editText)
        } else {
            // For options, show a TextView with the question
            val textView = TextView(requireContext())
            textView.text = question.text
            binding.questionContainer.addView(textView)
        }

        // Update timeline
        updateTimeline()

        // Show or hide the previous button
        binding.btnPrevious.visibility = if (currentQuestionIndex == 0) View.GONE else View.VISIBLE

        // Show AI suggestions if this is the last question
        binding.aiSuggestionText.visibility = if (question.isSuggestion) View.VISIBLE else View.GONE
        binding.aiSuggestionText.text = "Coming Soon"
    }

    private fun updateTimeline() {
        // Reset timeline colors
        for (i in 1..5) {
            val stepTextView = binding.timelineView.findViewWithTag<TextView>("timeline_step_$i")
            stepTextView?.let {
                it.setTextColor(resources.getColor(R.color.grey, null))
            }
        }

        // Highlight the current step
        val currentStepTextView = binding.timelineView.findViewWithTag<TextView>("timeline_step_${currentQuestionIndex + 1}")
        currentStepTextView?.let {
            it.setTextColor(resources.getColor(R.color.colorPrimary, null))
        }
    }


    private fun onNextButtonClick() {
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            updateQuestionView()
        }
    }

    private fun onPreviousButtonClick() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--
            updateQuestionView()
        }
    }
}

data class Question(val text: String, val isOpenEnded: Boolean, val isSuggestion: Boolean = false)
