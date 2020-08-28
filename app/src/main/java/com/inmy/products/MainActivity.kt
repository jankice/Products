package com.inmy.products

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO temp .. redirect to login

        val intent = Intent(this, HomeActivty::class.java)
        startActivity(intent)

    }
}