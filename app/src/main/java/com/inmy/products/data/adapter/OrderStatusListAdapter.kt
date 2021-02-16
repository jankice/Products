package com.inmy.products.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.R
import com.inmy.products.data.holder.OrderStatusListViewHolder
import com.inmy.products.data.model.OrderStatusResponseModel
import com.inmy.products.ui.order.OrderStatusViewModel

class OrderStatusListAdapter(context: Context,
                             private var orderStatusViewModel: OrderStatusViewModel
)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listOfOrderStatus = listOf<OrderStatusResponseModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v: View

        v = LayoutInflater.from(parent.context).inflate(R.layout.item_order_status, parent, false)

        return OrderStatusListViewHolder(parent.context,v)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val vh: OrderStatusListViewHolder = viewHolder as OrderStatusListViewHolder
        vh.bindView(listOfOrderStatus[position],orderStatusViewModel)
    }

    override fun getItemCount(): Int {
        return listOfOrderStatus.size
    }

    fun setOrderList(list: ArrayList<OrderStatusResponseModel>) {

        this.listOfOrderStatus = list
        notifyDataSetChanged()

    }

}
