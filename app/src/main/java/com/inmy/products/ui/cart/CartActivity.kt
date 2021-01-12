package com.inmy.products.ui.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.inmy.products.R
import com.inmy.products.REF_CART_DETAIL
import com.inmy.products.REF_PRODUCT_DETAIL
import com.inmy.products.data.adapter.CartListAdapter
import com.inmy.products.data.model.CartModel
import com.inmy.products.databinding.ActivityCartBinding
import kotlinx.android.synthetic.main.activity_cart.*


class CartActivity : AppCompatActivity() {
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartList : ArrayList<CartModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityCartBinding = DataBindingUtil.setContentView(this,R.layout.activity_cart)

        binding.lifecycleOwner = this

        cartViewModel =
            this.run { ViewModelProvider(this).get(CartViewModel::class.java) }

        binding.cartViewModel = cartViewModel

        cartList  = intent.getParcelableArrayListExtra(REF_CART_DETAIL)

        initRecyclerView()

    }

    private fun initRecyclerView() {
        cartRecycleView.layoutManager = LinearLayoutManager(this)

        val cartListAdapter = CartListAdapter(this, cartList)
        cartRecycleView.adapter = cartListAdapter



    }
}