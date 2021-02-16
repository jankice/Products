package com.inmy.products

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast

val REF_PRODUCT_DETAIL = "101"
val REF_CART_DETAIL = "102"
val REQ_SIGN_IN = 1000
val PREFERENCE_FILE_NAME = "productPreferenceFile"
val PREFERENCE_FILE_CART = "cartPreferenceFile"
val PREFERENCE_KEY_CART_TOTAL = "cart_Total"
val PREFERENCE_KEY_ID_TOKEN = "id_token"

fun showToast(msg: String, context: Context?) {
        Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
}

fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false // Initial Value
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}



