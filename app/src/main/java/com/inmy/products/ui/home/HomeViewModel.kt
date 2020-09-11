package com.inmy.products.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inmy.products.Utils
import com.inmy.products.data.model.HomeRepository
import com.inmy.products.data.model.ProductModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var homeRepository: HomeRepository?=null
    var postModelListLiveData : MutableLiveData<List<ProductModel>>?=null
    var pageNo : Int = 0
    var cartVal: Int = 0
    val utils: Utils   = Utils()
    private var _result = MutableLiveData<String>().apply { value = "0" }
    val result: LiveData<String>
        get() = _result

    init {
        homeRepository = HomeRepository()
        postModelListLiveData = MutableLiveData()
        pageNo = pagination("0",pageNo)
        fetchAllPosts(pageNo,"")
    }


    fun update(result: String){
        _result.value = result
        fetchAllPosts(pageNo,result)
    }

    @SuppressLint("CheckResult")
    fun fetchAllPosts(page: Int, result: String){
        homeRepository?.fetchAllPosts(page,result)?.subscribeOn(Schedulers.io())
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
        }
        return page;
    }

    fun nextClicked() {

        val pageCurr: Int = pagination("NEXT",pageNo)
        pageNo = pageCurr
        _result.value?.let { fetchAllPosts(pageCurr, it) }
    }

    fun prevClicked() {

        val pageCurr: Int = pagination("PREV",pageNo)
        pageNo = pageCurr
        _result.value?.let { fetchAllPosts(pageCurr, it) }
    }

    fun addClicked(){
        val addCart = utils.count(1,cartVal)
        cartVal = addCart

    }

    fun removeClicked(){
        val removeCart = utils.count(0,cartVal)
        cartVal = removeCart

    }

}