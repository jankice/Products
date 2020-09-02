package com.inmy.products.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.R
import com.inmy.products.data.holder.ProductListViewHolder
import com.inmy.products.data.model.ProductModel
import com.inmy.products.ui.home.HomeFragment

class ProductListAdapter(private val cellClickListener: HomeFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface CellClickListener {
        fun onCellClickListener(productModel: ProductModel)
    }

    private var listOfProducts = listOf<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listOfProducts.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val productViewHolder = viewHolder as ProductListViewHolder
        productViewHolder.bindView(listOfProducts[position])
        productViewHolder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(listOfProducts[position])
        }

    }

    fun setProductList(listOfProducts: List<ProductModel>) {
        this.listOfProducts = listOfProducts
        notifyDataSetChanged()
    }


}