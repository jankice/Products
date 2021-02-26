package com.inmy.products.ui.admin.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.inmy.products.Products
import com.inmy.products.R
import com.inmy.products.data.room.data.ProductRepository
import com.inmy.products.databinding.FragmentAddressBinding
import com.inmy.products.databinding.FragmentProductListBinding
import com.inmy.products.ui.address.AddressViewModel
import com.inmy.products.ui.address.AddressViewModelFactory
import com.inmy.products.ui.admin.productUpload.AdminProductUploadViewModel


class ProductListFragment : Fragment() {


    private lateinit var productListViewModel: ProductListViewModel
    private lateinit var productRepository: ProductRepository
   // val productRepository = (activity?.application as Products).repositoryProducts

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProductListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)


        productListViewModel = activity?.run {
            ViewModelProvider(this)[ProductListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        binding.productListViewModel = productListViewModel


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}