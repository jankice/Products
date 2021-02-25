package com.inmy.products.ui.admin.productUpload

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inmy.products.data.model.Dimen
import com.inmy.products.data.model.HomeRepository
import com.inmy.products.data.model.ProductRequestModel
import kotlinx.android.synthetic.main.fragment_admin_product_upload.view.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AdminProductUploadViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private var homeRepository: HomeRepository?=null

    var submitCartRequest: Boolean? = false

    init {
        homeRepository = HomeRepository(context)
    }


    fun submitProduct(productRequestModel: ProductRequestModel) : Boolean?{
        viewModelScope.launch {
            async {
                val result = homeRepository?.requestSubmitProduct(productRequestModel)
                submitCartRequest = result
            }

        }
        return submitCartRequest
    }

    fun addImageViewForImage(context: Context) : ImageView {
        val imageView = ImageView(context)


       return imageView
    }

    fun checkProductDetailValidations() : Boolean{

        return false
    }
}