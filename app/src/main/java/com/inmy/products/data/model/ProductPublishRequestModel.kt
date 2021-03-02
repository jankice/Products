package com.inmy.products.data.model

import com.google.gson.annotations.SerializedName

class ProductPublishRequestModel(
    @SerializedName("ImageUrl") var productImageUrl: String?,
    @SerializedName("Name") var productTitle: String?,
    @SerializedName("Description") var productDetailSort: String?,
    @SerializedName("Description_Long") var productDetailLong: String?,
    @SerializedName("Dimension") var dimenList: Dimen,
    @SerializedName("Category") var category: String?,
    @SerializedName("SubCategory") var subCategory: String?,
    @SerializedName("Price") var price: Int
)

class Dimen(
    var length: Int = 0,
    var width: Int = 0,
    var height: Int = 0

)
