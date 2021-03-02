package com.inmy.products.ui.admin.dashboard

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.*
import com.inmy.products.data.model.*
import com.inmy.products.data.room.data.Product
import com.inmy.products.data.room.data.ProductRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ProductListViewModel(context: Context, private val productRepository: ProductRepository) : ViewModel() {
    val allProducts: LiveData<List<Product>> = productRepository.allProducts.asLiveData()
    var publishProductRequestModelListLiveData : MutableLiveData<Resources<PublishResponse>>? = null
    private var homeRepository: HomeRepository?=null
    init{
        homeRepository = HomeRepository(context)
    }

    fun submitProduct(productPublishRequestModel: ProductPublishRequestModel) {
        viewModelScope.launch {
            async {
                val result = homeRepository?.requestSubmitProduct(productPublishRequestModel)
                publishProductRequestModelListLiveData?.value = result
            }
            publishProductRequestModelListLiveData?.value = Resources.loading()
        }

    }
}
class ProductViewModelFactory(
    private val context: Context,
    private val productRepository: ProductRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductListViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ProductListViewModel(context,productRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}