package com.inmy.products.data.model

import com.google.gson.annotations.SerializedName

class PlaceOrderResponseModel (
    @SerializedName("Cost") val cost: String?,
    @SerializedName("ID") val id: Int?,
    @SerializedName("CreatedOn") val createdOn: String,
    @SerializedName("EstimatedDelivery") val estDelivery: String?,
    @SerializedName("IsCancellable") val cancellable: Boolean?,
    @SerializedName("OrderStatus") val status: String
)