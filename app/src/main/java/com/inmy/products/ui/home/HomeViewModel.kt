package com.inmy.products.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.inmy.products.data.model.HomeRepository
import com.inmy.products.data.model.ProductModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var homeRepository: HomeRepository?=null
    var postModelListLiveData : MutableLiveData<List<ProductModel>>?=null
    var pageNo : Int = 0

    init {
        homeRepository = HomeRepository()
        postModelListLiveData = MutableLiveData()
        pageNo = pagination("0",pageNo)
        fetchAllPosts(pageNo)
    }

    @SuppressLint("CheckResult")
    fun fetchAllPosts(page : Int){
        homeRepository?.fetchAllPosts(page)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe( {
            postModelListLiveData?.value = it
        },
            {
                    // todo handle
            }
        )
    }

    fun pagination(s: String, old: Int): Int {

        var page : Int = old;

        if(s.equals("NEXT")){
            page ++
        }else if(s.equals("PREV") && page > 0){
            page --
        }else{

        }
        return page;
    }

    fun nextClicked() {

        val pageCurr: Int = pagination("NEXT",pageNo)
        pageNo = pageCurr
        fetchAllPosts(pageCurr)
    }

    fun prevClicked() {

        val pageCurr: Int = pagination("PREV",pageNo)
        pageNo = pageCurr
        fetchAllPosts(pageCurr)
    }

}