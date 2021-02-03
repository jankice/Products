package com.inmy.products.ui.home

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.inmy.products.*
import com.inmy.products.data.model.*
import kotlinx.android.synthetic.main.fragment_home.*
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
    private var cartcount: Int = 0

    init {
        homeRepository = HomeRepository(context)

        postModelListLiveData = MutableLiveData()
        cartResponseModelListLiveData = MutableLiveData()
        mcartValue = MutableLiveData()

        updateCart()
        pageNo = pagination("0",pageNo)

        cartResponse()
        fetchAllPosts(pageNo,"")
    }

    fun updateCart(){

        mcartValue!!.value = Preference(context, PREFERENCE_FILE_CART).getValueFromPReference(
            PREFERENCE_KEY_CART_TOTAL,"0").toInt()
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
        cartVal = Preference(context, PREFERENCE_FILE_CART).getValueFromPReference(productId,"0").toInt()

        cartVal += 1

        Preference(context, PREFERENCE_FILE_CART).saveValueToPreference(productId,cartVal.toString())

        requestCart(CartRequestModel(productId.toInt() ,cartVal))

        cartResponse()

        return cartVal

    }

    fun removeClicked(context: Context,productId: String): Int{
        cartVal = Preference(context, PREFERENCE_FILE_CART).getValueFromPReference(productId,"0").toInt()

        if(cartVal > 0){
            cartVal -=1
        }
        Preference(context, PREFERENCE_FILE_CART).saveValueToPreference(productId,cartVal.toString())


        requestCart(CartRequestModel(productId.toInt() ,cartVal))

        cartResponse()

        return cartVal
    }

    fun getTotalCartValueFromResponse(data: List<CartResponseModel>?): Int{
        var count = 0
        for(quantity in data!!){
            count = quantity.quantity?.plus(count) ?: 0
        }
        mcartValue?.value = count
        Preference(context, PREFERENCE_FILE_CART).saveValueToPreference(PREFERENCE_KEY_CART_TOTAL,count.toString())

        return count
    }

}