package com.inmy.products.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.Utils
import com.inmy.products.HomeActivty
import com.inmy.products.R
import com.inmy.products.data.Decoration.GridItemDecoration
import com.inmy.products.data.adapter.ProductListAdapter
import com.inmy.products.data.model.ProductModel
import com.inmy.products.ui.productdetail.ProductDetailActivity

class HomeFragment : Fragment(), ProductListAdapter.CellClickListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeViewModel: HomeViewModel
    private val utils: Utils = Utils()
    private lateinit var recyclerViewProducts : RecyclerView
    private lateinit var nextButton : Button
    private lateinit var prevButton : Button

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
                utils.showToast("Something went wrong",context)
            }

        })

    }

    override fun onCellClickListener(productModel: ProductModel) {
        utils.showToast(productModel.productTitle,context)

        val intent = Intent(context, ProductDetailActivity::class.java)
        intent.putExtra(utils.REF_PRODUCT_DETAIL,productModel)
        startActivity(intent)
    }
}

