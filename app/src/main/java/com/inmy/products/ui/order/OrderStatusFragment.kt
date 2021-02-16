package com.inmy.products.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.inmy.products.R
import com.inmy.products.data.adapter.OrderStatusListAdapter
import com.inmy.products.data.adapter.ProductListAdapter
import com.inmy.products.data.model.OrderStatusResponseModel
import com.inmy.products.data.model.ProductModel
import com.inmy.products.data.model.Resources
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_order_status.*

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

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycleView()
    }

    private fun initRecycleView() {
        recyclerViewOrderStatus.layoutManager = GridLayoutManager(context, 2)

        val orderStatusListAdapter = OrderStatusListAdapter(requireActivity(),orderStatusViewModel)
        recyclerViewOrderStatus.adapter = orderStatusListAdapter

        orderStatusViewModel.orderStatusResponseModelListLiveData?.observe(viewLifecycleOwner, {
            when (it.status) {
                Resources.Status.SUCCESS -> {
                 //   progressbarLoadingProduct.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()){
                        orderStatusListAdapter.setOrderList(it.data as ArrayList<OrderStatusResponseModel>)
                    }
                }
                Resources.Status.FAILURE ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()

                Resources.Status.LOADING ->{
                  //  progressbarLoadingProduct.visibility = View.VISIBLE
                }

            }

        })
    }
}