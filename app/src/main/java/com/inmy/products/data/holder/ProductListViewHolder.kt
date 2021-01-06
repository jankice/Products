package com.inmy.products.data.holder

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmy.products.R
import com.inmy.products.Utils
import com.inmy.products.data.model.ProductModel
import com.inmy.products.ui.home.HomeFragment
import kotlinx.android.synthetic.main.item_product.view.*

class ProductListViewHolder(var context: Context, itemView: View) : RecyclerView.ViewHolder(itemView){


    var sharedPreferences: SharedPreferences = context.getSharedPreferences(Utils.PREFERENCE_FILE_NAME,
        Context.MODE_PRIVATE)

    fun bindView(productModel: ProductModel, cellClickListener: HomeFragment) {
        itemView.textProductTitle.text = productModel.productTitle
        itemView.textProductDetailSort.text = productModel.productDetailSort


        itemView.textViewProductPrice.text = context.getString(R.string.title_price) + ": " + productModel.price

        val cart = Utils.getValuesFromPreference(sharedPreferences,productModel.productId)
        itemView.textNumberItem.text = cart

        Glide.with(itemView.context).load(productModel.productImageUrl!!).into(itemView.imageProduct)

         itemView.imageProduct.setOnClickListener{
             cellClickListener.onCellClickListener(productModel)
         }

         itemView.buttonRemove.setOnClickListener {
             val cart = cellClickListener.onRemoveClicked(productModel.productId)
             itemView.textNumberItem.text = cart.toString()
         }

         itemView.buttonAdd.setOnClickListener {
             val cart = cellClickListener.onAddClicked(productModel.productId)
             itemView.textNumberItem.text = cart.toString()
         }


    }


}


