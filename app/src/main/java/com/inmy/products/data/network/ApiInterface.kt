package com.inmy.products.data.network

import com.inmy.products.data.model.CartResponseModel
import com.inmy.products.data.model.CartRequestModel
import com.inmy.products.data.model.ProductModel
import io.reactivex.Completable
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {
    @GET("api/products/")
    suspend fun fetchAllPosts(@Query("page") page: Int, @Query("query") query: String): Response<List<ProductModel>>

    @GET("api/users/cart/")
    suspend fun cartResponse() : Response<List<CartResponseModel>>

    @Headers("Content-Type: application/json")
    @POST("api/users/cart")
    suspend fun requestCart(@Body cartModel: CartRequestModel): Completable
}