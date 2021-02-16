package com.inmy.products.ui.order

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import com.inmy.products.data.model.CartResponseModel
import com.inmy.products.data.model.HomeRepository
import com.inmy.products.data.model.OrderStatusResponseModel
import com.inmy.products.data.model.Resources
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class OrderStatusViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private var homeRepository: HomeRepository?=null
    var orderStatusResponseModelListLiveData : MutableLiveData<Resources<List<OrderStatusResponseModel>>>? = null
    init {

        homeRepository = HomeRepository(context)
        orderStatusResponseModelListLiveData = MutableLiveData()
        orderStatusResponse()
    }

    fun orderStatusResponse(){
        viewModelScope.launch {
            async {
                var result = homeRepository?.responseOrderStatus()
                orderStatusResponseModelListLiveData?.value = result
            }
            orderStatusResponseModelListLiveData?.value = Resources.loading()
        }
    }
}