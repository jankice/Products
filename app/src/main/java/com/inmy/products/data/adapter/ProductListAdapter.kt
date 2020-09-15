package com.inmy.products.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.R
import com.inmy.products.data.holder.ProductListViewHolder
import com.inmy.products.data.holder.ProductListViewHolderFooter
import com.inmy.products.data.holder.ProductListViewHolderHeader
import com.inmy.products.data.model.ProductModel
import com.inmy.products.ui.home.HomeFragment

class ProductListAdapter(private val cellClickListener: HomeFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface CellClickListener {
        fun onCellClickListener(productModel: ProductModel)
        fun onNextClicked()
        fun onPrevClicked()
        fun onAddClicked(productId: String): Int
        fun onRemoveClicked(productId: String): Int
    }

    val FOOTER_TYPE : Int = 1
    val HEADER_TYPE : Int = 2

    private var listOfProducts = listOf<ProductModel>()


    override fun getItemViewType(position: Int): Int {

         if (position ==  listOfProducts.size) {

             return FOOTER_TYPE
         }
//         else if(position == 0){
//            return HEADER_TYPE
//         }
        return 0

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == FOOTER_TYPE){
            return ProductListViewHolderFooter(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_product_footer,
                    parent, false
                )
            )
        }else if(viewType == HEADER_TYPE){
            return ProductListViewHolderHeader(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_product_header,
                    parent, false
                )
            )
        }
        else{
            return ProductListViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_product,
                    parent,
                    false
                )
            )
        }

    }

    override fun getItemCount(): Int = listOfProducts.size + 1

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        try {
            if (viewHolder is ProductListViewHolder) {
                val vh: ProductListViewHolder = viewHolder as ProductListViewHolder
                vh.bindView(listOfProducts[position],cellClickListener)

            } else if (viewHolder is ProductListViewHolderFooter) {
                val vh: ProductListViewHolderFooter = viewHolder as ProductListViewHolderFooter
                vh.bindViewFooter(listOfProducts[position],cellClickListener)
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