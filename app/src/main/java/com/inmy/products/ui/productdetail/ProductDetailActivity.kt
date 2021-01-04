package com.inmy.products.ui.productdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.inmy.products.R
import com.inmy.products.Utils
import com.inmy.products.data.adapter.ImageSliderAdapter
import com.inmy.products.data.model.ProductModel
import com.inmy.products.databinding.ActivityProductDetailBinding
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var productDetailViewModel: ProductDetailViewModel
    private val utils: Utils = Utils()
    private lateinit var product : ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityProductDetailBinding =
            DataBindingUtil.setContentView(this,R.layout.activity_product_detail)

        binding.lifecycleOwner = this

        productDetailViewModel =
            this.run { ViewModelProvider(this).get(ProductDetailViewModel::class.java) }

        binding.productDetailViewModel = productDetailViewModel


         product  = intent.getParcelableExtra(utils.REF_PRODUCT_DETAIL) as ProductModel


        setValuesforProduct()

    }

    private fun setValuesforProduct() {

        val adapter = ImageSliderAdapter(this,product.productImageUrls)
        viewpager.adapter = adapter
//
//        Glide.with(this).load(product.productImageUrl).into(imageProductDetail)
        textProductTitleDetail.text = product.productTitle
        textProductDetail.text = product.productDetailSort

    }
}