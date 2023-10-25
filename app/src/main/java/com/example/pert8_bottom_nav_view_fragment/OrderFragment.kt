package com.example.pert8_bottom_nav_view_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.pert8_bottom_nav_view_fragment.databinding.FragmentOrderBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            ticketType.setOnClickListener {
                val action = OrderFragmentDirections.actionOrderFragmentToPickerFragment()
                findNavController().navigate(action)
            }

            findNavController().currentBackStackEntry?.
            savedStateHandle?.getLiveData<String>("ticket")?.observe(viewLifecycleOwner) {
                res -> ticketType.setText(res)
            }

            btnBuyTicket.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}