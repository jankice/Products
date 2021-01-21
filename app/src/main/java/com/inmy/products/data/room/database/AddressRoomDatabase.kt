package com.inmy.products.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.inmy.products.data.room.dao.AddressDAO
import com.inmy.products.data.room.data.Address
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Address::class), version = 1, exportSchema = false)
public abstract class AddressRoomDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDAO

    private class WordDatabaseCallback(private val scope:CoroutineScope
    ):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let{ database ->
                scope.launch {
                    var addressDAO = database.addressDao()
                    addressDAO.deleteAll()

                    //adding sample
                    var address = Address(name = "janki", mobile = "7045215979")
                    addressDAO.insert(address)
                }
            }
        }
    }
    companion object{
        @Volatile
        private var INSTANCE: AddressRoomDatabase? = null

        fun getDatabase(context: Context,
        scope: CoroutineScope): AddressRoomDatabase{

            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                AddressRoomDatabase::class.java,
                "address_database").build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }
}