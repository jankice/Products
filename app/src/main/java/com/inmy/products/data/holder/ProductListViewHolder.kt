package com.inmy.products.data.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmy.products.PREFERENCE_FILE_CART
import com.inmy.products.R
import com.inmy.products.data.model.Preference
import com.inmy.products.data.model.ProductModel

import com.inmy.products.ui.home.HomeFragment
import com.inmy.products.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.item_product.view.*

class ProductListViewHolder(var context: Context, itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    lateinit var productModel: ProductModel
    lateinit var cellClickListener: HomeFragment
    lateinit var homeViewModel: HomeViewModel

    fun bindView(
        productModel: ProductModel,
        cellClickListener: HomeFragment,
        homeViewModel: HomeViewModel
    ) {

        this.homeViewModel = homeViewModel
        this.productModel = productModel
        this.cellClickListener = cellClickListener

        val cart = Preference(context, PREFERENCE_FILE_CART).getValueFromPReference(productModel.productId,"0")

        itemView.apply {
            textProductTitle.text = productModel.productTitle
            textProductDetailSort.text = productModel.productDetailSort
            textViewProductPrice.text = context.resources.getString(R.string.rs) + ". " + productModel.price
            textNumberItem.text = cart

            imageProduct.setOnClickListener(this@ProductListViewHolder)
            buttonRemove.setOnClickListener(this@ProductListViewHolder)
            buttonAdd.setOnClickListener(this@ProductListViewHolder)
        }

        Glide.with(itemView.context).load(productModel.productImageUrl!!).into(itemView.imageProduct)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imageProduct ->{
                cellClickListener.onCellClickListener(productModel)
            }
            R.id.buttonAdd ->{
                val cart = homeViewModel.addClicked(context,productModel.productId)
                itemView.textNumberItem.text = cart.toString()
            }
            R.id.buttonRemove ->{
                val cart = homeViewModel.removeClicked(context,productModel.productId)
                itemView.textNumberItem.text = cart.toString()
            }
        }

    }
}


