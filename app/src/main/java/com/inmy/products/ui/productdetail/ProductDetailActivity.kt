package com.inmy.products.ui.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inmy.products.R

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var productDetailViewModel: ProductDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)


    }
}