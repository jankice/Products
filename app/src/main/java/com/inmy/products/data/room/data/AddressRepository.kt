package com.inmy.products.data.room.data

import androidx.annotation.WorkerThread
import com.inmy.products.data.room.dao.AddressDAO
import kotlinx.coroutines.flow.Flow

class AddressRepository(private val addressDAO: AddressDAO) {

    val allAddress: Flow<List<Address>> = addressDAO.getAddress()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(address: Address){
        addressDAO.insert(address)
    }
}