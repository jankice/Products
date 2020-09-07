package com.inmy.products

import android.content.Context
import android.widget.Toast
import java.io.Serializable

class Utils  : Serializable{


    fun showToast(msg: String, context: Context?) {

        Toast.makeText(context,msg, Toast.LENGTH_LONG).show()

    }


         val REF_PRODUCT_DETAIL = "101"

}