package com.inmy.products.ui.home

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.*
import com.inmy.products.*
import com.inmy.products.data.model.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private var homeRepository: HomeRepository?=null
    var postModelListLiveData : MutableLiveData<Resources<List<ProductModel>>>?=null
    var cartResponseModelListLiveData : MutableLiveData<Resources<List<CartResponseModel>>>? = null
    private var pageNo : Int = 0
    private var cartVal: Int = 0
    private var _result = MutableLiveData<String>().apply { value = "" }

    var mcartValue : MutableLiveData<Int>? = null
    private var cartcount = checkValuesFromPreference(context, PREFERENCE_KEY_CART_TOTAL)

    init {
        homeRepository = HomeRepository(context)
        postModelListLiveData = MutableLiveData()
        cartResponseModelListLiveData = MutableLiveData()
        mcartValue = MutableLiveData()

        updateCart(cartcount)
        pageNo = pagination("0",pageNo)

        cartResponse()
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



    fun cartResponse(){
        viewModelScope.launch {
            async {
                val result = homeRepository?.cartResponse()
                cartResponseModelListLiveData?.value = result
            }
            cartResponseModelListLiveData?.value = Resources.loading()
        }
    }
    fun requestCart(cartModel: CartRequestModel){
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
        cartVal += 1
        valueToPreference(context,productId,cartVal.toString(), CONST_SAVE)

        requestCart(CartRequestModel(productId.toInt() ,cartVal))
        cartResponse()
        //todo after completing request get response cart data and update cart value
        return cartVal

    }

    fun removeClicked(context: Context,productId: String): Int{
        cartVal = checkValuesFromPreference(context,productId)
        if(cartVal > 0){
            cartVal -=1
        }
        valueToPreference(context,productId,cartVal.toString(), CONST_SAVE)

        requestCart(CartRequestModel(productId.toInt() ,cartVal))
        cartResponse()
        //todo after completing request get response cart data and update cart value
        return cartVal
    }

    fun getPreviousSavedCartValue(data: List<CartResponseModel>?): Int{
        var count: Int = 0
        for(quantity in data!!){
            count = quantity.quantity?.plus(count) ?: 0
        }
        valueToPreference(context, PREFERENCE_KEY_CART_TOTAL,count.toString(), CONST_SAVE)
        return count
    }

    fun totalCartValue(context: Context, trigger: Int): Int{
        cartcount =  checkValuesFromPreference(context, PREFERENCE_KEY_CART_TOTAL)
        if(trigger == 0 && cartcount > 0){
            cartcount--
        }else{
            cartcount++
        }
        mcartValue?.value = cartcount
        valueToPreference(context, PREFERENCE_KEY_CART_TOTAL,cartcount.toString(), CONST_SAVE)
        return cartcount
    }

    fun checkValuesFromPreference(context: Context,productId: String): Int{

        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME,
            Context.MODE_PRIVATE)
        val value = getValuesFromPreference(sharedPreferences,productId)

        return value.toInt()
    }

}