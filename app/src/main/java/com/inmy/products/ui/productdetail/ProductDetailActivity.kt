package com.inmy.products.ui.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.inmy.products.R
import com.inmy.products.Utils
import com.inmy.products.data.model.ProductModel
import com.inmy.products.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.item_product.view.*

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var productDetailViewModel: ProductDetailViewModel

    private lateinit var mImageProductDetail: ImageView
    private lateinit var mTextProductTitleDetail: TextView
    private lateinit var mTextProductDetail: TextView
    private val utils: Utils = Utils()
    private lateinit var product : ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        productDetailViewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)


         product  = intent.getParcelableExtra(utils.REF_PRODUCT_DETAIL) as ProductModel


        mImageProductDetail = findViewById(R.id.imageProductDetail)
        mTextProductTitleDetail = findViewById(R.id.textProductTitleDetail)
        mTextProductDetail = findViewById(R.id.textProductDetail)


        setValuesforProduct()







    }

    private fun setValuesforProduct() {

        Glide.with(this).load(product.productImageUrl).into(mImageProductDetail)
        mTextProductTitleDetail.text = product.productTitle
        mTextProductDetail.text = product.productDetailSort



    }
}