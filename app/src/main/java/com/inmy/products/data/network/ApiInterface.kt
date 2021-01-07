package com.inmy.products.data.network

import android.app.DownloadManager
import com.inmy.products.data.model.CartModel
import com.inmy.products.data.model.ProductModel
import io.reactivex.Completable
import okhttp3.Call
import okhttp3.Request
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {
    @GET("api/products/")
    suspend fun fetchAllPosts(@Query("page") page: Int, @Query("query") query: String): Response<List<ProductModel>>

    @Headers("Content-Type: application/json")
    @POST("api/users/cart")
    suspend fun requestCart(@Body cartModel: CartModel): Completable
}