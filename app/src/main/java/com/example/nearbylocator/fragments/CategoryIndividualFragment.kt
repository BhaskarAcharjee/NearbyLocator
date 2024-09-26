package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nearbylocator.R
import com.example.nearbylocator.adapters.DineoutVertiImageAdapter
import com.example.nearbylocator.databinding.FragmentCategoryIndividualBinding
import com.example.nearbylocator.utils.dineoutMoreList

class CategoryIndividualFragment : Fragment() {

    private lateinit var binding: FragmentCategoryIndividualBinding
    private lateinit var dineOutVertiAdapter: DineoutVertiImageAdapter

    private val hintStrings = arrayOf(
        "Buhari Hotel",
        "Palmshore",
        "Royal Le Meridian",
        "Purva Windermare",
        "The Orange Palace"
    )
    private var currentHintIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryIndividualBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            // Setup vertical RecyclerView for more around you
            rvMorearoundyou.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            dineOutVertiAdapter = DineoutVertiImageAdapter(dineoutMoreList)
            rvMorearoundyou.adapter = dineOutVertiAdapter

            svDineout.viewTreeObserver.addOnScrollChangedListener {
                val linearLayoutHeight = llSearchbar.height + llMedian.height
                val scrollY = svDineout.scrollY

                if (scrollY >= linearLayoutHeight) {
                    llSearchbar.visibility = View.VISIBLE
                } else {
                    llSearchbar.visibility = View.GONE
                }
            }

            textSwitcher.setFactory {
                val textView = TextView(context)
                textView.textSize = 16f
                textView.typeface =
                    ResourcesCompat.getFont(requireContext(), R.font.swiggy_font_regular)
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                textView
            }

            switchText()
        }
    }

    private fun switchText() {
        binding.textSwitcher.setText(hintStrings[currentHintIndex])
        currentHintIndex = (currentHintIndex + 1) % hintStrings.size

        binding.textSwitcher.postDelayed(
            { switchText() },
            1500
        ) // Delay between text switches (2 seconds in this example)
    }
}
