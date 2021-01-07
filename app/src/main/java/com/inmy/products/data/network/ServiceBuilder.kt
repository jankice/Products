package com.inmy.products.data.network

import android.util.Log
import com.inmy.products.getValuesFromPreference
import com.inmy.products.sharedPreferences
import okhttp3.*

class ServiceBuilder : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().addHeader("Authorization", "Bearer "+ sharedPreferences?.let {
           getValuesFromPreference(
                it,"id_token")
        }).build()

        val response = chain.proceed(request)
        Log.d("aa",""+response.code())
        Log.d("aa",""+response.body())
        when (response.code()) {

            400 -> {
                Log.d("aa","Bad Request")
                //Show Bad Request Error Message
            }
            401 -> {
                Log.d("aa","UnauthorizedError")
                //Show UnauthorizedError Message
            }

            403 -> {
                Log.d("aa","Forbidden")
                //Show Forbidden Message
            }

            404 -> {
                Log.d("aa","NotFound")
                //Show NotFound Message
            }


            // ... and so on

        }
        return response

    }
}