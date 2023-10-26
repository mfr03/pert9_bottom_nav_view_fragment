package com.example.pert8_bottom_nav_view_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.example.pert8_bottom_nav_view_fragment.databinding.FragmentPickerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PickerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PickerFragment : Fragment() {

    private lateinit var binding: FragmentPickerBinding
    private var selectedItem : String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPickerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val ticketAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, resources.getStringArray(R.array.spinner_ticket))
            spinnerTicket.setAdapter(ticketAdapter)

            spinnerTicket.setOnTouchListener { v, event ->
                if(event.action == MotionEvent.ACTION_UP) {
                    val rightDrawable = (v as AutoCompleteTextView).compoundDrawables[2]
                    if(event.rawX >= (rightDrawable.bounds.left - v.right)) {
                        spinnerTicket.showDropDown()
                        return@setOnTouchListener true
                    }
                }
                false
            }

            spinnerTicket.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                selectedItem = ticketAdapter.getItem(position).toString()
            }

            btnBuyTicket.setOnClickListener {
                findNavController().apply {
                    previousBackStackEntry?.
                    savedStateHandle?.set("ticket", (selectedItem)?: "Tiket")
                }.navigateUp()
            }
        }
    }
}