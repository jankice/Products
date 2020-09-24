package com.inmy.products.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inmy.products.R

class OrderStatusFragment : Fragment() {

    private lateinit var orderStatusViewModel: OrderStatusViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        orderStatusViewModel =
            ViewModelProvider(this).get(OrderStatusViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_order_status, container, false)
        val textView: TextView = root.findViewById(R.id.text_order_status)
        orderStatusViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }
}