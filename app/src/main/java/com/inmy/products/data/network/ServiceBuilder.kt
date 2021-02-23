package com.inmy.products.data.network

import android.content.Context
import android.util.Log
import com.inmy.products.PREFERENCE_FILE_NAME
import com.inmy.products.PREFERENCE_KEY_ID_TOKEN
import com.inmy.products.data.model.Preference
import okhttp3.*

class ServiceBuilder(context: Context) : Interceptor {
    var context: Context? = context
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        var token = context?.let {
            Preference(it, PREFERENCE_FILE_NAME).getValueFromPReference(
                PREFERENCE_KEY_ID_TOKEN,"")
        }

        request = request.newBuilder().addHeader("Authorization","Bearer "+token ).build()
                                                                                                                                                                                 val response = chain.proceed(request)
        Log.d("aa",""+response.code())
        Log.d("aa",""+response.body())

        when (response.code()) {

            400 -> {
                Log.d("aa","Bad Request")

            }
            401 -> {
                Log.d("aa","UnauthorizedError")

            }

            403 -> {
                Log.d("aa","Forbidden")

            }

            404 -> {
                Log.d("aa","NotFound")

            }




        }
        return response

    }
}