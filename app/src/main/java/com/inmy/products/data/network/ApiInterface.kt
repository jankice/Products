package com.inmy.products.data.network

import com.inmy.products.data.model.ProductModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/products")
    fun fetchAllPosts(): Call<List<ProductModel>>
}