package com.inmy.products.data.holder

import com.inmy.products.data.model.ProductModel

interface IBindView {
    fun bindView(productModel: ProductModel)
}
