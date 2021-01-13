package com.inmy.products.data.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmy.products.data.model.CartResponseModel
import kotlinx.android.synthetic.main.item_cart.view.*

class CartListViewHolder(var context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(cartResponseModel: CartResponseModel) {
        itemView.cartItemName.text = cartResponseModel.productTitle
        itemView.cartItemShortDescription.text = cartResponseModel.productDetailSort
        itemView.cartItemPrice.text = cartResponseModel.price.toString()
        itemView.cartItemQtySpinner.prompt = cartResponseModel.quantity.toString()
        Glide.with(itemView.context).load(cartResponseModel.productImageUrl!!).into(itemView.cartItemImage)

    }

}