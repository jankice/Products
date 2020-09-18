package com.inmy.products.data.holder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmy.products.data.model.ProductModel
import com.inmy.products.ui.home.HomeFragment
import kotlinx.android.synthetic.main.item_product.view.*
import kotlinx.android.synthetic.main.item_product_footer.view.*

class ProductListViewHolderFooter(itemView: View) : RecyclerView.ViewHolder(itemView){
  //  super(itemView)
    fun bindViewFooter(cellClickListener: HomeFragment) {


         itemView.btnNextHome.setOnClickListener {
             Log.d("next","clicked")
             cellClickListener.onNextClicked()
         }

         itemView.btnPrevHome.setOnClickListener {
             Log.d("prev","clicked")
             cellClickListener.onPrevClicked()

         }
    }
}
