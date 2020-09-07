package com.inmy.products.data.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmy.products.data.model.ProductModel
import kotlinx.android.synthetic.main.item_product.view.*

class ProductListViewHolderSuper(itemView: View, val viewType : Int) : RecyclerView.ViewHolder(itemView) {

    fun bindView(productModel: ProductModel) {
        itemView.textProductTitle.text = productModel.productTitle
        itemView.textProductDetailSort.text = "Views: " + productModel.productDetailSort
        itemView.textNumberItem.text =""+ productModel.productCart

        Glide.with(itemView.context).load(productModel.productImageUrl!!).into(itemView.imageProduct)
        if(viewType == 1){
            
        }
    }
}