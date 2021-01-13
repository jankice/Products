package com.inmy.products.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.R
import com.inmy.products.data.holder.CartListViewHolder
import com.inmy.products.data.model.CartResponseModel
import com.inmy.products.ui.cart.CartActivity

class CartListAdapter(cartActivity: CartActivity, cartResponseList: List<CartResponseModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var listOfCartItem = cartResponseList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View

        v = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)

        return CartListViewHolder(parent.context,v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh: CartListViewHolder = holder as CartListViewHolder
        vh.bindView(listOfCartItem.get(position))
    }

    override fun getItemCount(): Int {
        return listOfCartItem.size
    }



}