package com.inmy.products.ui.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inmy.products.data.model.HomeRepository
import com.inmy.products.data.model.ProductModel
import java.io.BufferedReader

class HomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var homeRepository: HomeRepository?=null
    var postModelListLiveData : LiveData<List<ProductModel>>?=null

    init {
        homeRepository = HomeRepository()
        postModelListLiveData = MutableLiveData()
    }

    fun fetchAllPosts(){
        postModelListLiveData = homeRepository?.fetchAllPosts()
    }
}