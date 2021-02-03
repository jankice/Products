package com.inmy.products.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.inmy.products.R
import com.inmy.products.REF_PRODUCT_DETAIL
import com.inmy.products.data.model.Resources
import com.inmy.products.data.adapter.ProductListAdapter
import com.inmy.products.data.model.ProductModel
import com.inmy.products.databinding.FragmentHomeBinding
import com.inmy.products.ui.productdetail.ProductDetailActivity
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), ProductListAdapter.CellClickListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        homeViewModel = activity?.run {
            ViewModelProvider(this)[HomeViewModel::class.java]
        } ?: throw Exception("Invalid Activity")


        binding.homeViewModel = homeViewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        handleSearchListener()
        initRecycleView()
    }


    private fun initRecycleView() {
        productRecycleView.layoutManager = GridLayoutManager(context, 2)

        val productListAdapter = ProductListAdapter(this,homeViewModel)
        productRecycleView.adapter = productListAdapter



        homeViewModel.postModelListLiveData?.observe(viewLifecycleOwner, {
            when (it.status) {
                Resources.Status.SUCCESS -> {
                    progressbarLoadingProduct.visibility = GONE
                    if (!it.data.isNullOrEmpty()){
                        productListAdapter.setProductList(it.data as ArrayList<ProductModel>)
                    }
                }
                Resources.Status.FAILURE ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()

                Resources.Status.LOADING ->{
                    progressbarLoadingProduct.visibility = VISIBLE
                }

            }

        })

    }

    private fun handleSearchListener() {
        searchViewHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                Toast.makeText(context, "Result: $query", Toast.LENGTH_LONG).show()
                homeViewModel.update(query)
                return false
            }

            override fun onQueryTextChange(text: String): Boolean {
                // Toast.makeText(context, "Result: $text", Toast.LENGTH_LONG).show()
                return false
            }
        })

        searchViewHome.addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
            override fun onViewDetachedFromWindow(arg0: View) {
                // search was detached/closed
                homeViewModel.update("")
            }

            override fun onViewAttachedToWindow(arg0: View) {
                // search was opened
            }
        })


    }

    // Functions from ProductListAdapter
    override fun onCellClickListener(productModel: ProductModel) {
        val intent = Intent(context, ProductDetailActivity::class.java)
        intent.putExtra(REF_PRODUCT_DETAIL, productModel)
        startActivity(intent)
    }

}

