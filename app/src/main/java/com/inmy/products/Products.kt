package com.inmy.products

import android.app.Application
import com.inmy.products.data.room.data.AddressRepository
import com.inmy.products.data.room.data.ProductRepository
import com.inmy.products.data.room.database.AddressRoomDatabase
import com.inmy.products.data.room.database.ProductRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Products : Application(){
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AddressRoomDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { AddressRepository(database.addressDao()) }

    val databaseProducts by lazy { ProductRoomDatabase.getDatabase(this,applicationScope) }
    val repositoryProducts by lazy { ProductRepository(databaseProducts.productsDao()) }
}