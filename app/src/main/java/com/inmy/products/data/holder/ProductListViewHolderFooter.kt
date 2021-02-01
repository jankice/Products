package com.inmy.products.data.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.ui.home.HomeFragment
import kotlinx.android.synthetic.main.item_product_footer.view.*

class ProductListViewHolderFooter(itemView: View) : RecyclerView.ViewHolder(itemView){
   // super(itemView)
    fun bindViewFooter(cellClickListener: HomeFragment) {


         itemView.btnNextHome.setOnClickListener {

             cellClickListener.onNextClicked()
         }

         itemView.btnPrevHome.setOnClickListener {

             cellClickListener.onPrevClicked()

         }
    }
}
