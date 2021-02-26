package com.inmy.products.data.room.data

import androidx.annotation.WorkerThread
import com.inmy.products.Products
import com.inmy.products.data.room.dao.ProductsDAO
import kotlinx.coroutines.flow.Flow

class ProductRepository (private val productsDAO: ProductsDAO) {

    val allProducts: Flow<List<Product>> = productsDAO.getProducts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(product: Product){
        productsDAO.insert(product)
    }
}