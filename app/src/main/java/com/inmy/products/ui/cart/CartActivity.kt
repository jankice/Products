package com.inmy.products.ui.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.inmy.products.R
import com.inmy.products.data.adapter.CartListAdapter
import com.inmy.products.data.model.CartResponseModel
import com.inmy.products.data.model.Resources
import com.inmy.products.databinding.ActivityCartBinding
import kotlinx.android.synthetic.main.activity_cart.*


class CartActivity : AppCompatActivity() {
    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityCartBinding = DataBindingUtil.setContentView(this,R.layout.activity_cart)

        binding.lifecycleOwner = this

        cartViewModel =
            this.run { ViewModelProvider(this).get(CartViewModel::class.java) }

        binding.cartViewModel = cartViewModel

        cartViewModel.cartResponseModelListLiveData?.observe(this, {
            when (it.status) {
                Resources.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()){
                        var list :ArrayList<CartResponseModel> = it.data as ArrayList<CartResponseModel>
                        initRecyclerView(list)
                    }
                }
                Resources.Status.FAILURE ->
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()

                Resources.Status.LOADING ->
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }

        })


    }

    private fun initRecyclerView(list: ArrayList<CartResponseModel>) {
        cartRecycleView.layoutManager = LinearLayoutManager(this)

        val cartListAdapter = CartListAdapter(this, list)
        cartRecycleView.adapter = cartListAdapter



    }
}