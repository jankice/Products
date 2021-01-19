package com.inmy.products.data.holder

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmy.products.R
import com.inmy.products.data.model.CartResponseModel
import kotlinx.android.synthetic.main.item_cart.view.*

class CartListViewHolder(var context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n")
    fun bindView(cartResponseModel: CartResponseModel) {
        itemView.cartItemName.text = cartResponseModel.productTitle
        itemView.cartItemShortDescription.text = cartResponseModel.productDetailSort
        itemView.cartItemPrice.text = context.resources.getString(R.string.rs) + ". " + cartResponseModel.price.toString()

        itemView.cartItemQty.text = "Qty." + cartResponseModel.quantity.toString()
        Glide.with(itemView.context).load(cartResponseModel.productImageUrl!!).into(itemView.cartItemImage)

    }

}