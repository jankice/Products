package com.inmy.products.data.model

import com.google.gson.annotations.SerializedName

data class CartModel (
    @SerializedName("productId") val productId: Int?,
    @SerializedName("quantity") val quantity: Int?, )

