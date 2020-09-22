package com.inmy.products.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bumptech.glide.load.engine.Resource
import com.inmy.products.Resources
import io.reactivex.Observable
import com.inmy.products.data.network.ApiInterface
import com.inmy.products.data.network.AppClient
import kotlinx.coroutines.Dispatchers
import retrofit2.Call


class HomeRepository {

    private var apiInterface:ApiInterface?=null

    init {
        apiInterface = AppClient.getApiClient().create(ApiInterface::class.java)
    }

     suspend fun fetchAllPosts(page: Int, result: String): Resources<List<ProductModel>> {


         try {
             val response = apiInterface?.fetchAllPosts(page,result)
             if (response?.isSuccessful!!) {
                 val body = response?.body()
                 if (body != null) return Resources.success(body)
             }
             return error(" ${response?.code()} ${response?.message()}")
         } catch (e: Exception) {
             return error(e.message ?: e.toString())
         }

//        apiInterface?.fetchAllPosts(page,"")?.enqueue(object : Callback<List<ProductModel>> {
//
//            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
//                data.value = null
//            }
//
//            override fun onResponse(
//                call: Call<List<ProductModel>>,
//                response: Response<List<ProductModel>>
//            ) {
//
//                val res = response.body()
//                if (response.code() == 200 &&  res!=null){
//                    data.value = res
//                }else{
//                    data.value = null
//                }
//
//            }
//        })
//
//        return data


    }
    private fun <T> error(message: String): Resources<T> {
       // Timber.d(message)r
        return Resources.error("Network call has failed for a following reason: $message")
    }

}
