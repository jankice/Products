package com.inmy.products

import android.content.Context
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast


val REF_PRODUCT_DETAIL = "101"
val REF_CART_DETAIL = "102"

val REQ_SIGN_IN = 1000
val REQ_IMAGE_FROM_GALLARY = 1001
val REQ_IMAGE_FROM_CAMERA = 1002
val PREFERENCE_FILE_NAME = "productPreferenceFile"
val PREFERENCE_FILE_CART = "cartPreferenceFile"
val PREFERENCE_KEY_CART_TOTAL = "cart_Total"
val PREFERENCE_KEY_ID_TOKEN = "id_token"

fun showToast(msg: String, context: Context?) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false // Initial Value
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

fun getRealPathFromUri(context: Context, contentUri: Uri?): String? {
    var cursor: Cursor? = null
    return try {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        cursor = contentUri?.let { context.contentResolver.query(it, proj, null, null, null) }
        val column_index: Int = (cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) ?: cursor?.moveToFirst()) as Int
        cursor?.getString(column_index)
    } finally {
        if (cursor != null) {
            cursor.close()
        }
    }
}



