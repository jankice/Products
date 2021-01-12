package com.inmy.products.data.model

import android.content.Context
import com.inmy.products.data.network.ApiInterface
import com.inmy.products.data.network.AppClient

class HomeRepository(context: Context) {

    private var apiInterface:ApiInterface?=null

    init {
        apiInterface = AppClient.getApiClient().create(ApiInterface::class.java)
        apiInterface = AppClient.authenticateApiClient(context).create(ApiInterface::class.java)

    }

    suspend fun requestCart(cartModel: CartModel){

        try {
            apiInterface?.requestCart(cartModel)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    suspend fun cartResponse(): Resources<List<CartModel>>{
        try {
            val response = apiInterface?.cartResponse()
            if (response?.isSuccessful!!) {
                val body = response.body()
                if (body != null) return Resources.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

     suspend fun fetchAllPosts(page: Int, result: String): Resources<List<ProductModel>> {


         try {
             val response = apiInterface?.fetchAllPosts(page,result)
             if (response?.isSuccessful!!) {
                 val body = response.body()
                 if (body != null) return Resources.success(body)
             }
             return error(" ${response.code()} ${response.message()}")
         } catch (e: Exception) {
             return error(e.message ?: e.toString())
         }

    }
    private fun <T> error(message: String): Resources<T> {
       // Timber.d(message)r
        return Resources.error("Network call has failed for a following reason: $message")
    }

}
