package com.inmy.products.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.R
import com.inmy.products.data.holder.ProductListViewHolder
import com.inmy.products.data.holder.ProductListViewHolderFooter
import com.inmy.products.data.model.ProductModel
import com.inmy.products.ui.home.HomeFragment
import com.inmy.products.ui.home.HomeViewModel


class ProductListAdapter(private val cellClickListener: HomeFragment, homeViewModel: HomeViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface CellClickListener {
        fun onCellClickListener(productModel: ProductModel)
    }

    private val  homeViewModel = homeViewModel
    private val FOOTER_TYPE : Int = 1

    private var listOfProducts = listOf<ProductModel>()


    override fun getItemViewType(position: Int): Int {

        if (position == listOfProducts.size) {
            // This is where we'll add footer.
            return FOOTER_TYPE
        }

        return super.getItemViewType(position)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        val v: View

        if (viewType == FOOTER_TYPE) {
            v = LayoutInflater.from(parent.context).inflate(
                R.layout.item_product_footer,
                parent,
                false
            )
            return ProductListViewHolderFooter(v)
        }

        v = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)

        return ProductListViewHolder(parent.context,v)

    }



    override fun getItemCount(): Int {

        if (listOfProducts.size == 0) {
            return 1
        }

        return listOfProducts.size + 1
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        try {
            if (viewHolder is ProductListViewHolder) {
                val vh: ProductListViewHolder = viewHolder
                vh.bindView(listOfProducts[position],cellClickListener,homeViewModel)
            } else if (viewHolder is ProductListViewHolderFooter) {
                val vh: ProductListViewHolderFooter = viewHolder
                vh.bindViewFooter(homeViewModel)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    fun setProductList(listOfProducts: List<ProductModel>) {

        this.listOfProducts = listOfProducts
        notifyDataSetChanged()
    }
}