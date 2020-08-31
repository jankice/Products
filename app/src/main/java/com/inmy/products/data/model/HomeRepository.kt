package com.inmy.products.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.inmy.products.data.network.ApiInterface
import com.inmy.products.data.network.AppClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeRepository {

    private var apiInterface:ApiInterface?=null

    init {
        apiInterface = AppClient.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchAllPosts():LiveData<List<ProductModel>>{
        val data = MutableLiveData<List<ProductModel>>()

        apiInterface?.fetchAllPosts()?.enqueue(object : Callback<List<ProductModel>> {

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<List<ProductModel>>,
                response: Response<List<ProductModel>>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    data.value = null
                }

            }
        })

        return data

    }


}
