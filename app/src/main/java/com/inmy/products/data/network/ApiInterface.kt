package com.inmy.products.data.network

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import com.inmy.products.data.model.ProductModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/products/")
    suspend fun fetchAllPosts(@Query("page") page: Int, @Query("query") query: String): Response<List<ProductModel>>
}