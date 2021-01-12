package com.inmy.products.data.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmy.products.data.model.CartModel
import kotlinx.android.synthetic.main.item_cart.view.*
import kotlinx.android.synthetic.main.item_product.view.*

class CartListViewHolder(var context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(cartModel: CartModel) {
        itemView.cartItemName.text = cartModel.productTitle
        itemView.cartItemShortDescription.text = cartModel.productDetailSort
        itemView.cartItemPrice.text = cartModel.price.toString()
        itemView.cartItemQtySpinner.prompt = cartModel.quantity.toString()
        Glide.with(itemView.context).load(cartModel.productImageUrl!!).into(itemView.cartItemImage)

    }

}