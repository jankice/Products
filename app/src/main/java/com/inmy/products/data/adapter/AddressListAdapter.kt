package com.inmy.products.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.R
import com.inmy.products.data.holder.AddressViewHolder
import com.inmy.products.data.room.data.Address

class AddressListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listOfAddress = listOf<Address>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View
        v = LayoutInflater.from(parent.context).inflate(R.layout.item_address, parent, false)
        return AddressViewHolder(v)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh: AddressViewHolder = holder as AddressViewHolder
        vh.bindView(listOfAddress.get(position))
    }

    override fun getItemCount(): Int {
        return listOfAddress.size
    }

    fun submitList(list: List<Address>) {
       this.listOfAddress = list
        notifyDataSetChanged()

    }


}




