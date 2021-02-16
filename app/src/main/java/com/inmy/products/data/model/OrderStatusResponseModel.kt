package com.inmy.products.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

class OrderStatusResponseModel (
    @SerializedName("Cost") val cost: String?,
    @SerializedName("ID") val id: Int?,
    @SerializedName("CreatedOn") val createdOn: String,
    @SerializedName("EstimatedDelivery") val estDelivery: String?,
    @SerializedName("IsCancellable") val cancellable: Boolean?,
    @SerializedName("OrderStatus") val orderStatus: String,
    @SerializedName("OrderDetails") val details: List<OrderDetail>
)

class OrderDetail (
    @SerializedName("ProductID") val productId: Int?,
    @SerializedName("Quantity") val qty: Int?,
    @SerializedName("Price") val price: Int,
        )


