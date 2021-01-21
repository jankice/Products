package com.inmy.products.data.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.data.room.data.Address
import kotlinx.android.synthetic.main.item_address.view.*

class AddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(address: Address) {
        itemView.textViewDisplayAddress.text = address.name
    }


}
