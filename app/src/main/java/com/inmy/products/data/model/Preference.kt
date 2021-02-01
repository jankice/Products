package com.inmy.products.data.model

import android.content.Context
import android.content.SharedPreferences

 class Preference(context: Context, fileName : String) {

    // val instance = Preference.create()
//     lateinit var context: Context
//     lateinit var fileName: String
//
//     companion object Factory {
//         fun create(): Preference = Preference()
//     }

//        constructor(context: Context, fileName : String) : this(){
//            this.context = context
//            this.fileName = fileName
//         }


        var sharedPreferences: SharedPreferences? = context.getSharedPreferences(
         fileName, Context.MODE_PRIVATE)

        fun saveValueToPreference(key: String, value: String){
             val editor: SharedPreferences.Editor? =  sharedPreferences?.edit()

             editor?.putString(key,value)
             editor?.apply()
         }

         fun clearValueFromPreference(){
             val editor = sharedPreferences?.edit()
             editor?.clear()
             editor?.apply()
         }

         fun getValueFromPReference(key: String, defValue : String): String{
             var value = sharedPreferences?.getString(key,defValue)!!
             return value
         }
     }



