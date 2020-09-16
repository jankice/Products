package com.inmy.products.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnAttachStateChangeListener
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.R
import com.inmy.products.Utils
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
    private lateinit var searchViewHome: androidx.appcompat.widget.SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerViewProducts = root.findViewById(R.id.productRecycleView)
        searchViewHome = root.findViewById(R.id.searchViewHome)


        handleSearchListener()
        initRecycleView()

        return root;
    }



    private fun initRecycleView() {
        recyclerViewProducts.layoutManager = GridLayoutManager(context, 2)

        //This will for default android divider
      //  recyclerViewProducts.addItemDecoration(GridItemDecoration(10, 2))

        val productListAdapter = ProductListAdapter(requireContext(),this)
        recyclerViewProducts.adapter = productListAdapter


        homeViewModel.postModelListLiveData?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                productListAdapter.setProductList(it as ArrayList<ProductModel>)
            } else {
                utils.showToast("Something went wrong", context)
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
    override fun onCellClickListener(productModel: ProductModel) {
        utils.showToast(productModel.productTitle, context)

        val intent = Intent(context, ProductDetailActivity::class.java)
        intent.putExtra(utils.REF_PRODUCT_DETAIL, productModel)
        startActivity(intent)
    }

    override fun onNextClicked() {
        homeViewModel.nextClicked()

    }

    override fun onPrevClicked() {
        homeViewModel.prevClicked()
    }

    override fun onAddClicked(productId: String): Int {
      val cart = homeViewModel.addClicked(requireContext(),productId)

        return cart
    }

    override fun onRemoveClicked(productId: String): Int {
       val cart = homeViewModel.removeClicked(requireContext(),productId)

        return cart
    }
}

