package com.inmy.products

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

import java.io.Serializable

class Utils : Serializable{


    val REF_PRODUCT_DETAIL = "101"
    val PREFERENCE_FILE_NAME = "productPreference"


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
        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(PREFERENCE_FILE_NAME,Context.MODE_PRIVATE)
        if(task == "SAVE"){
            val editor: SharedPreferences.Editor =  sharedPreferences.edit()

            editor.putString(key,value)
            editor.apply()
            //editor.apply()
        }
        else if(task == "CLEAR"){
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
        }
    }

    fun getValuesFromPreference(sharedPreferences: SharedPreferences, key: String): String{

        var value = "0"
        value = sharedPreferences.getString(key,value)!!
        return value
    }






}