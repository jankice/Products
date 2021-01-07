package com.inmy.products.ui.home

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.*
import com.inmy.products.PREFERENCE_FILE_NAME
import com.inmy.products.count
import com.inmy.products.data.model.Resources
import com.inmy.products.data.model.CartModel
import com.inmy.products.data.model.HomeRepository
import com.inmy.products.data.model.ProductModel
import com.inmy.products.getValuesFromPreference
import com.inmy.products.valueToPreference
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext


    private var homeRepository: HomeRepository?=null
    var postModelListLiveData : MutableLiveData<Resources<List<ProductModel>>>?=null

    var pageNo : Int = 0
    var cartVal: Int = 0
    private var _result = MutableLiveData<String>().apply { value = "" }
    var cartModel: CartModel? = null
    var mcartValue : MutableLiveData<Int>? = null
    private var cartcount = checkValuesFromPreference(context,"cart_Total")

    init {
        homeRepository = HomeRepository()
        postModelListLiveData = MutableLiveData()
        mcartValue = MutableLiveData()
        updateCart(cartcount)
        pageNo = pagination("0",pageNo)
        requestCart(CartModel(2,5))
        fetchAllPosts(pageNo,"")
    }


    fun updateCart(count: Int){
        mcartValue!!.value = count
    }
    fun update(result: String){
        _result.value = result
        fetchAllPosts(pageNo,result)
    }


    fun fetchAllPosts(page: Int, query: String) {
        viewModelScope.launch {
            async {
                var result = homeRepository?.fetchAllPosts(page, query )
                postModelListLiveData?.value =result
            }
            postModelListLiveData?.value = Resources.loading()
        }
    }
    fun requestCart(cartModel: CartModel){
        viewModelScope.launch {
                async {
                    homeRepository?.requestCart(cartModel)
                }
        }
    }
    fun pagination(s: String, old: Int): Int {

        var page : Int = old

        if(s.equals("NEXT")){
            page ++
        }else if(s.equals("PREV") && page > 0){
            page --
        }
        return page
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
        val addCart = count(1,cartVal)
        cartVal = addCart

        valueToPreference(context,productId,cartVal.toString(),"SAVE")
        return cartVal

    }

    fun removeClicked(context: Context,productId: String): Int{
        cartVal = checkValuesFromPreference(context,productId)
        val removeCart = count(0,cartVal)
        cartVal = removeCart
        valueToPreference(context,productId,cartVal.toString(),"SAVE")
        return cartVal

    }

    fun totalCartValue(context: Context, count: Int): Int{
        cartcount =  checkValuesFromPreference(context,"cart_Total")
        if(count == 0 && cartcount > 0){
            cartcount--
        }else{
            cartcount++
        }
        mcartValue?.value = cartcount
        valueToPreference(context,"cart_Total",cartcount.toString(),"SAVE")
        return cartcount
    }

    fun checkValuesFromPreference(context: Context,productId: String): Int{

        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME,
            Context.MODE_PRIVATE)
        val value = getValuesFromPreference(sharedPreferences,productId)

        return value.toInt()
    }

}