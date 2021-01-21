package com.inmy.products.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inmy.products.data.room.data.Address
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDAO {
    @Query("SELECT * FROM address_table ORDER By id ASC")
    fun getAddress(): Flow<List<Address>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(name: Address)

    @Query("DELETE FROM address_table")
    suspend fun deleteAll()
}