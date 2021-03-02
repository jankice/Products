package com.inmy.products.data.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inmy.products.data.model.Dimen
import com.inmy.products.data.model.ProductPublishRequestModel
import com.inmy.products.data.room.data.Product
import com.inmy.products.ui.admin.dashboard.ProductListViewModel
import kotlinx.android.synthetic.main.item_saved_product.view.*

class SavedProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindView(product: Product, productListViewModel: ProductListViewModel) {
            Glide.with(itemView.context).load(product.P_image).into(itemView.imageViewProductDefaultImage)

        itemView.apply {
                textProductName.text = product.P_name
                textProductDetail.text = product.P_detail
                textProductPrize.text = product.P_price.toString()

                productButtonDetail.setOnClickListener{

                }

                productButtonPublish.setOnClickListener {
                    //Todo commented code move to publish products

                    val productPublishRequestModel = ProductPublishRequestModel(product.P_image,product.P_name,product.P_detail,"",
                        Dimen(product.P_length,product.P_width,product.P_height),product.P_category,product.P_subCategory,
                        product.P_price)

                    productListViewModel.submitProduct(productPublishRequestModel)

                }

                ImageButtonProductDelete.setOnClickListener {

                }

            }
    }

}
