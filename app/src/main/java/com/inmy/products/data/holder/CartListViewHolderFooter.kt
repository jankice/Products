package com.inmy.products.data.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.data.model.CartResponseModel
import com.inmy.products.ui.cart.CartActivity
import kotlinx.android.synthetic.main.item_cart_footer.view.*
import java.text.NumberFormat
import java.util.*

class CartListViewHolderFooter(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindViewFooter(
        listOfCartItem: List<CartResponseModel>,
        cartClickListener: CartActivity
    ) {

        itemView.cartPriceText.text = "Price ( " + listOfCartItem.size + " Items )"
        itemView.cartDiscount.text = ""
        val delivery = listOfCartItem.size * 5
        itemView.cartDeliveryCharge.text = (listOfCartItem.size * 5).toString()
        val price = countPrice(listOfCartItem)
        itemView.cartPrice.text = price.toString()
        itemView.cartTotalAmount.text = NumberFormat.getNumberInstance(Locale.US).format(delivery + price)
        //(delivery + price).toString()

        itemView.buttonPlaceOrder.setOnClickListener {
                cartClickListener.onPlaceOrderClicked()
        }


    }

    fun countPrice(listOfCartItem: List<CartResponseModel>) : Double{

       var totalPrice = listOfCartItem.fold(0.0, { acc, cartResponseModel ->
           acc+ cartResponseModel.price * cartResponseModel.quantity!!  })
        return totalPrice
    }

}
