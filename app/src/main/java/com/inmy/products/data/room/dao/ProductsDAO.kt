package com.inmy.products.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inmy.products.data.room.data.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDAO {
    @Query("SELECT * FROM product_table ORDER By id ASC")
    fun getProducts(): Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(name: Product)

    @Query("DELETE FROM product_table")
    suspend fun deleteAll()
}