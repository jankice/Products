package com.inmy.products.ui.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inmy.products.R

class AddressFragment : Fragment() {

    private lateinit var addressViewModel: AddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addressViewModel =
            ViewModelProvider(this).get(AddressViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_address, container, false)
        val textView: TextView = root.findViewById(R.id.text_address)
        addressViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }
}