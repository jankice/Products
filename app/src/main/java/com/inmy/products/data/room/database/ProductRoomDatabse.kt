package com.inmy.products.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.inmy.products.data.room.dao.ProductsDAO
import com.inmy.products.data.room.data.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Product::class), version = 1, exportSchema = false)
abstract class ProductRoomDatabase : RoomDatabase(){
    abstract fun productsDao(): ProductsDAO

    private class WordDatabaseCallback(private val scope: CoroutineScope
    ):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let{ database ->
                scope.launch {
                    val productsDao = database.productsDao()
                    productsDao.deleteAll()

                }
            }
        }
    }
    companion object{
        @Volatile
        private var INSTANCE: ProductRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): ProductRoomDatabase{

            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    ProductRoomDatabase::class.java,
                    "products_database")

                    .build()
                INSTANCE = instance

                instance
            }
        }

    }
}