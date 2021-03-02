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

    suspend fun responseOrderStatus(): Resources<List<OrderStatusResponseModel>>{
        try {
            val response = apiInterface?.orderStatusResponse()
            if (response?.isSuccessful!!) {
                val body = response.body()
                if (body != null) return Resources.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }
    suspend fun requestOrders(placeOrderRequestModel: PlaceOrderRequestModel) : Resources<PlaceOrderResponseModel>{
        try {
            val response = apiInterface?.requestOrders(placeOrderRequestModel)
            if (response?.isSuccessful!!) {
                val body = response.body()
                if (body != null) return Resources.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    suspend fun requestCart(cartRequestModel: CartRequestModel) : Boolean {
        try {
           val response = apiInterface?.requestCart(cartRequestModel)
            if(response?.isSuccessful!!){
                return true
            }
            return false
        }catch (e: Exception){
            e.printStackTrace()
           return false
        }
    }

    suspend fun requestSubmitProduct(productPublishRequestModel: ProductPublishRequestModel): Resources<PublishResponse>{
        try {
            val response = apiInterface?.requestSubmitProduct(productPublishRequestModel)
            if(response?.isSuccessful!!){
                val body = response.body()
                if (body != null) return Resources.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        }catch (e: Exception){
            e.printStackTrace()
            return error(e.message ?: e.toString())
        }
    }

    suspend fun cartResponse(): Resources<List<CartResponseModel>>{
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
             val response = apiInterface?.fetchAllPosts(page, result)
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

        return Resources.error("Network call has failed for a following reason: $message")
    }

}
