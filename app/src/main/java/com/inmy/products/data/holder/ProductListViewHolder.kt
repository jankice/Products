package com.inmy.products.data.holder

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmy.products.HomeActivty
import com.inmy.products.data.model.ProductModel
import kotlinx.android.synthetic.main.item_product.view.*

class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(productModel: ProductModel) {
        itemView.textProductTitle.text = productModel.productTitle
        itemView.textProductDetailSort.text = "Views: " + productModel.productDetailSort
        itemView.textNumberItem.text =""+ productModel.productCart

        Glide.with(itemView.context).load(productModel.productImageUrl!!).into(itemView.imageProduct)

    }




}


