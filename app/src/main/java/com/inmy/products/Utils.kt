package com.inmy.products

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast


val REF_PRODUCT_DETAIL = "101"
val REQ_SIGN_IN = 1000
val PREFERENCE_FILE_NAME = "productPreferenceFile"
val PREFERENCE_KEY_CART_TOTAL = "cart_Total"
val PREFERENCE_KEY_ID_TOKEN = "id_token"

val CONST_SAVE = "SAVE"
val CONST_CLEAR = "CLEAR"
var sharedPreferences: SharedPreferences? = null

        fun getValuesFromPreference(sharedPreferences: SharedPreferences, key: String): String{

            var value = "0"
            value = sharedPreferences.getString(key,value)!!
            return value
        }

        fun showToast(msg: String, context: Context?) {
        Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
         }

    fun count(trigger: Int, value: Int): Int{
        var curValue  = value
        if(trigger == 0 && curValue > 0){
            curValue --
        }else if(trigger == 1){
            curValue ++
        }
        return curValue
    }

    fun valueToPreference(context: Context?, key: String, value: String, task: String){
         sharedPreferences = context!!.getSharedPreferences(PREFERENCE_FILE_NAME,Context.MODE_PRIVATE)
        if(task == "SAVE"){
            val editor: SharedPreferences.Editor? =  sharedPreferences?.edit()

            editor?.putString(key,value)
            editor?.apply()
            //editor.apply()
        }
        else if(task == "CLEAR"){
            val editor = sharedPreferences?.edit()
            editor?.clear()
            editor?.apply()
        }
    }


