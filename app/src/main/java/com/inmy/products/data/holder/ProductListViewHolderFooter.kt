package com.inmy.products.data.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.inmy.products.ui.home.HomeFragment
import com.inmy.products.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.item_product_footer.view.*

class ProductListViewHolderFooter(itemView: View) : RecyclerView.ViewHolder(itemView){
   // super(itemView)
    fun bindViewFooter(homeViewModel: HomeViewModel) {


         itemView.btnNextHome.setOnClickListener {
            homeViewModel.nextClicked()

         }

         itemView.btnPrevHome.setOnClickListener {

             homeViewModel.prevClicked()

         }
    }
}
