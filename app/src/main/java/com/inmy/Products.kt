package com.inmy

import android.app.Application

class Products : Application(){
    override fun onCreate() {
        super.onCreate()
        //Cache.initialize(context = applicationContext)
    }
}