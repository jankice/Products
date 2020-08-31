package com.inmy.products.data.model

import com.google.gson.annotations.SerializedName

data class ProductModel  (
    @SerializedName("Id")  var productId: Int,
    @SerializedName("Name") var productTitle: String,
    @SerializedName("Description") var productDetailSort: String,
    var productCart: Int,
    var productPicture: Int?,
    @SerializedName ("ImageUrl") var productImageUrl: String?,
)