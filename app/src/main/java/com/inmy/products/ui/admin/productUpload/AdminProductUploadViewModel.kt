package com.inmy.products.ui.admin.productUpload

import android.app.Application
import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.inmy.products.data.model.HomeRepository
import com.inmy.products.data.model.ProductPublishRequestModel
import com.inmy.products.data.room.data.Product
import com.inmy.products.data.room.data.ProductRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AdminProductUploadViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private var homeRepository: HomeRepository?=null

    var submitCartRequest: Boolean? = false

    init {
        homeRepository = HomeRepository(context)
    }




    fun addImageViewForImage(context: Context) : ImageView {
        val imageView = ImageView(context)


       return imageView
    }

    fun checkProductDetailValidations() : Boolean{

        return true
    }

    fun insert(product: Product, productRepository: ProductRepository) = viewModelScope.launch {
        productRepository.insert(product)
    }
}