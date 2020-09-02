package com.inmy.products.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.R
import com.inmy.products.data.Decoration.GridItemDecoration
import com.inmy.products.data.adapter.ProductListAdapter
import com.inmy.products.data.model.ProductModel

class HomeFragment : Fragment(), ProductListAdapter.CellClickListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerViewProducts : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

         recyclerViewProducts = root.findViewById(R.id.productRecycleView)

        initRecycleView()

        return root;
    }


    private fun initRecycleView() {

        homeViewModel.fetchAllPosts()

        recyclerViewProducts.layoutManager = GridLayoutManager(context,2)

        //This will for default android divider
        recyclerViewProducts.addItemDecoration(GridItemDecoration(10, 2))

        val productListAdapter = ProductListAdapter(this)
        recyclerViewProducts.adapter = productListAdapter


        homeViewModel.postModelListLiveData?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                recyclerViewProducts.visibility = View.VISIBLE
                productListAdapter.setProductList(it as ArrayList<ProductModel>)
            } else {
                homeViewModel.showToast("Something went wrong",context)
            }

        })

    }

    override fun onCellClickListener(productModel: ProductModel) {
        homeViewModel.showToast(productModel.productTitle,context)
    }
}

