package com.inmy.products.data.model

import com.google.gson.annotations.SerializedName
import okhttp3.*
import java.io.IOException

data class CartModel (
    @SerializedName("ProductId") val userId: String?,
    @SerializedName("Quantity") val userName: String?,
        )

