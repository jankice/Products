package com.inmy.products

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.util.Base64.encodeToString
import android.widget.Toast
import java.io.*
import java.util.regex.Pattern


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
//val startTime = System.nanoTime()
//val endTime = System.nanoTime()
//val duration = endTime - startTime
//Log.d("duration",""+duration)

fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false // Initial Value
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

fun base64tiImageBitmap(string: String) : Bitmap{

    val imageBytes = Base64.decode(string, 0)
    val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    return image
}
fun convertToBase64(context: Context, imageUri: Uri): String{
    var fis: InputStream? = null
    try{
        fis = context.contentResolver.openInputStream(imageUri)!!
    } catch (e: Exception){
        e.printStackTrace()
    }

    val bm = BitmapFactory.decodeStream(fis)
    val baos = ByteArrayOutputStream()
    bm.compress(Bitmap.CompressFormat.JPEG, 50, baos)
    val b: ByteArray = baos.toByteArray()

    return encodeToString(b, Base64.DEFAULT)
}


 fun isBase64(stringBase64: String): Boolean {
    val regex = "([A-Za-z0-9+/]{4})*" +
            "([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)"
    val pattern: Pattern = Pattern.compile(regex)
    return pattern.matcher(stringBase64).matches()
}







