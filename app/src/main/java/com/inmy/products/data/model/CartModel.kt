package com.inmy.products.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CartModel (
    @SerializedName("ProductId") val productId: Int?,
    @SerializedName("Quantity") val quantity: Int?,
    @SerializedName ("ImageUrl") var productImageUrl: String?,
    @SerializedName("Description") var productDetailSort: String,
    @SerializedName("Name") var productTitle: String,
    @SerializedName("Price") var price: Int) :Parcelable

