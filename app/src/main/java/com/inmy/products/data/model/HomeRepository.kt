package com.inmy.products.data.model

import io.reactivex.Observable
import com.inmy.products.data.network.ApiInterface
import com.inmy.products.data.network.AppClient


class HomeRepository {

    private var apiInterface:ApiInterface?=null

    init {
        apiInterface = AppClient.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchAllPosts(page : Int):Observable<List<ProductModel>>?{
           return apiInterface?.fetchAllPosts(page,"")

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


}
