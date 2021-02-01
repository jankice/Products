package com.inmy.products.data.model

import com.google.gson.annotations.SerializedName

class PlaceOrderRequestModel(
    @SerializedName("DeliveryAddress") val address: String?,
    @SerializedName("PaymentMode") val paymentMode: Int?,
    @SerializedName("DeliveryPinCode") val pinCode: String
)