package com.inmy.products.ui.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.inmy.products.R
import com.inmy.products.Utils
import com.inmy.products.data.model.ProductModel
import com.inmy.products.databinding.ActivityProductDetailBinding
import com.inmy.products.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var productDetailViewModel: ProductDetailViewModel
    private val utils: Utils = Utils()
    private lateinit var product : ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityProductDetailBinding =
            DataBindingUtil.setContentView(this,R.layout.activity_product_detail)

        binding.lifecycleOwner = this

        productDetailViewModel =
            this.run { ViewModelProvider(this).get(ProductDetailViewModel::class.java) }

        binding.productDetailViewModel = productDetailViewModel


         product  = intent.getParcelableExtra(utils.REF_PRODUCT_DETAIL) as ProductModel


        setValuesforProduct()

    }

    private fun setValuesforProduct() {

        Glide.with(this).load(product.productImageUrl).into(imageProductDetail)
        textProductTitleDetail.text = product.productTitle
        textProductDetail.text = product.productDetailSort

    }
}