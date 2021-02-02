package com.inmy.products.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.R
import com.inmy.products.data.holder.CartListViewHolder
import com.inmy.products.data.holder.CartListViewHolderFooter
import com.inmy.products.data.model.CartResponseModel
import com.inmy.products.ui.cart.CartActivity

class CartListAdapter(cartActivity: CartActivity, cartResponseList: List<CartResponseModel>,
private val cartClickListener: CartActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    interface ClickListener{
            fun onPlaceOrderClicked()
    }
    private var listOfCartItem = cartResponseList
    private val FOOTER_TYPE : Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View

        if (viewType == FOOTER_TYPE) {
            v = LayoutInflater.from(parent.context).inflate(
                R.layout.item_cart_footer,
                parent,
                false
            )
            return CartListViewHolderFooter(v)
        }
        v = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)

        return CartListViewHolder(parent.context,v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            if (holder is CartListViewHolder) {
                val vh: CartListViewHolder = holder
                vh.bindView(listOfCartItem.get(position))
            } else if (holder is CartListViewHolderFooter) {
                    val vh: CartListViewHolderFooter = holder
                    vh.bindViewFooter(listOfCartItem,cartClickListener)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun getItemCount(): Int {

        if (listOfCartItem.size == 0) {
            return 1
        }

        return listOfCartItem.size + 1
    }

    override fun getItemViewType(position: Int): Int {

        if (position == listOfCartItem.size) {
            // This is where we'll add footer.
            return FOOTER_TYPE
        }

        return super.getItemViewType(position)

    }



}