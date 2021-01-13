package com.inmy.products.ui.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inmy.products.data.model.CartResponseModel
import com.inmy.products.data.model.HomeRepository
import com.inmy.products.data.model.Resources
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class CartViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private var homeRepository: HomeRepository?=null
    var cartResponseModelListLiveData : MutableLiveData<Resources<List<CartResponseModel>>>? = null
    init {

        homeRepository = HomeRepository(context)
        cartResponseModelListLiveData = MutableLiveData()
        cartResponse()
    }

    fun cartResponse(){
        viewModelScope.launch {
            async {
                var result = homeRepository?.cartResponse()
                cartResponseModelListLiveData?.value = result
            }
            cartResponseModelListLiveData?.value = Resources.loading()
        }
    }
}