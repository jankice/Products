package com.inmy.products.data.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmy.products.Utils
import com.inmy.products.data.model.ProductModel
import com.inmy.products.ui.home.HomeFragment
import kotlinx.android.synthetic.main.item_product.view.*

class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindView(productModel: ProductModel, cellClickListener: HomeFragment) {
        itemView.textProductTitle.text = productModel.productTitle
        itemView.textProductDetailSort.text = "Views: " + productModel.productDetailSort
        itemView.textNumberItem.text =""+ productModel.productCart

        Glide.with(itemView.context).load(productModel.productImageUrl!!).into(itemView.imageProduct)

         itemView.imageProduct.setOnClickListener{
             cellClickListener.onCellClickListener(productModel)
         }

         itemView.buttonRemove.setOnClickListener {
             val cart = cellClickListener.onRemoveClicked(productModel.productId.toString())
             itemView.textNumberItem.text = cart.toString()
         }

         itemView.buttonAdd.setOnClickListener {
             val cart = cellClickListener.onAddClicked(productModel.productId.toString())
             itemView.textNumberItem.text = cart.toString()
         }

    }


}


