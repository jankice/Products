package com.inmy.products.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.R
import com.inmy.products.data.holder.SavedProductViewHolder
import com.inmy.products.data.room.data.Product
import com.inmy.products.ui.admin.dashboard.ProductListViewModel

class SavedProductAdapter(private var productListViewModel: ProductListViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listOfSavedProducts = listOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View
        v = LayoutInflater.from(parent.context).inflate(R.layout.item_saved_product, parent, false)
        return SavedProductViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh: SavedProductViewHolder = holder as SavedProductViewHolder
        vh.bindView(listOfSavedProducts.get(position),productListViewModel)
    }

    override fun getItemCount(): Int {
        return listOfSavedProducts.size
    }

    fun ProductList(list: List<Product>) {
        this.listOfSavedProducts = list
        notifyDataSetChanged()

    }
}