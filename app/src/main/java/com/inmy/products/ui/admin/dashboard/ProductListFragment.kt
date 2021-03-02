package com.inmy.products.ui.admin.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.inmy.products.Products
import com.inmy.products.R
import com.inmy.products.data.adapter.SavedProductAdapter
import com.inmy.products.databinding.FragmentProductListBinding
import kotlinx.android.synthetic.main.fragment_product_list.*


class ProductListFragment : Fragment() {


    private val productListViewModel: ProductListViewModel by viewModels {

        ProductViewModelFactory(this.requireContext(),(activity?.application as Products).repositoryProducts)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentProductListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)


        binding.productListViewModel = productListViewModel


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecycleView()
    }

    private fun initRecycleView() {
        recyclerViewSavedProductList.layoutManager = LinearLayoutManager(context)

        val savedProductAdapter = SavedProductAdapter(productListViewModel)
        recyclerViewSavedProductList.adapter = savedProductAdapter



        activity?.let {
            productListViewModel.allProducts.observe(it) { product ->
                // Update the cached copy of the words in the adapter.
                product.let { savedProductAdapter.ProductList(it) }
            }
        }

    }

}