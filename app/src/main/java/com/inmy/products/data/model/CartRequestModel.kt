package com.inmy.products.data.model

import com.google.gson.annotations.SerializedName

class CartRequestModel (
    @SerializedName("ProductId") val productId: Int?,
    @SerializedName("Quantity") val quantity: Int?
)