package com.inmy.products.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inmy.products.R
import com.inmy.products.Resources
import com.inmy.products.Utils
import com.inmy.products.data.model.HomeRepository
import com.inmy.products.data.model.ProductModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var homeRepository: HomeRepository?=null
    var postModelListLiveData : MutableLiveData<Resources<List<ProductModel>>>?=null
    var pageNo : Int = 0
    var cartVal: Int = 0
    val utils: Utils   = Utils()
    private var _result = MutableLiveData<String>().apply { value = "" }
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

//    @SuppressLint("CheckResult")
//    fun<T> fetchAllPosts(page: Int, result: String) : Resources<T>{
//        homeRepository?.fetchAllPosts(page,result)?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe( {
//                postModelListLiveData?.value = it
//        },
//            {
//                   Log.d("Error",it.message,it)
//
//            }
//        )
//
//        return
//    }
    fun fetchAllPosts(page: Int, query: String) {
        viewModelScope.launch {
            async {
                var result = homeRepository?.fetchAllPosts(page, query )
                postModelListLiveData?.value =result
            }
            postModelListLiveData?.value = Resources.loading()
        }
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

    fun addClicked(context: Context,productId: String): Int{

        cartVal = checkValuesFromPreference(context,productId)
        val addCart = utils.count(1,cartVal)
        cartVal = addCart
        utils.valueToPreference(context,productId,cartVal.toString(),"SAVE")
        return cartVal

    }

    fun removeClicked(context: Context,productId: String): Int{
        cartVal = checkValuesFromPreference(context,productId)
        val removeCart = utils.count(0,cartVal)
        cartVal = removeCart
        utils.valueToPreference(context,productId,cartVal.toString(),"SAVE")
        return cartVal

    }

    fun checkValuesFromPreference(context: Context,productId: String): Int{

        val sharedPreferences: SharedPreferences = context.getSharedPreferences(utils.PREFERENCE_FILE_NAME,
            Context.MODE_PRIVATE)
        val value = utils.getValuesFromPreference(sharedPreferences,productId)

        return value.toInt()
    }

}