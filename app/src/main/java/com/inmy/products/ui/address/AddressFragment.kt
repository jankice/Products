package com.inmy.products.ui.address

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.inmy.products.Products
import com.inmy.products.R
import com.inmy.products.data.adapter.AddressListAdapter
import com.inmy.products.data.room.data.Address
import com.inmy.products.databinding.ActivityAddEditAddressBinding
import com.inmy.products.databinding.FragmentAddressBinding
import com.inmy.products.ui.home.HomeActivty
import kotlinx.android.synthetic.main.fragment_address.*


class AddressFragment : Fragment() {
    companion object {
        fun newInstance() = AddressFragment()
    }
   // private lateinit var addressViewModel: AddressViewModel

    private val newAddressActivityRequestCode = 1
    private val addressViewModel: AddressViewModel by viewModels {

        AddressViewModelFactory((activity?.application as Products).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentAddressBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_address, container, false)


        binding.addressViewModel = addressViewModel



        return binding.root
    }

    private fun setAdapter() {

        val adapter = AddressListAdapter()
        recyclerviewAddress.adapter = adapter
        recyclerviewAddress.layoutManager = LinearLayoutManager(requireContext())


        activity?.let {
            addressViewModel.allAddress.observe(it) { words ->
                // Update the cached copy of the words in the adapter.
                words.let { adapter.submitList(it) }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newAddressActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val address : Address = data?.getParcelableExtra(AddEditAddressActivity.EXTRA_ADDRESS) as Address
            addressViewModel.insert(address)
            setAdapter()

        } else {
            Toast.makeText(
                context,
                "R.string.empty_not_saved",
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()
        textViewAddAddress.setOnClickListener {
            val intent = Intent(requireContext(), AddEditAddressActivity::class.java)
            startActivityForResult(intent,newAddressActivityRequestCode)
        }

    }

}