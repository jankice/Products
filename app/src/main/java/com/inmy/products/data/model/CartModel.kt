package com.inmy.products.data.model

import com.google.gson.annotations.SerializedName

data class CartModel (
    @SerializedName("ProductId") val productId: Int?,
    @SerializedName("Quantity") val quantity: Int?,
        )

