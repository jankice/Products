package com.inmy.products.ui.offer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inmy.products.R

class OfferFragment : Fragment() {

    private lateinit var offerViewModel: OfferViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        offerViewModel =
            ViewModelProvider(this).get(OfferViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_offer, container, false)
        val textView: TextView = root.findViewById(R.id.text_offer)
        offerViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }
}