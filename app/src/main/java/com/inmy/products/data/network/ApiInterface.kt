package com.inmy.products.data.network

import io.reactivex.Observable
import com.inmy.products.data.model.ProductModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/products/")
    fun fetchAllPosts(@Query("page") page: Int, @Query("query") query: String): Observable<List<ProductModel>>
}