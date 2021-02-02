package com.inmy.products.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.inmy.products.data.room.dao.AddressDAO
import com.inmy.products.data.room.data.Address
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Address::class), version = 2, exportSchema = false)
 abstract class AddressRoomDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDAO

    private class WordDatabaseCallback(private val scope:CoroutineScope
    ):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let{ database ->
                scope.launch {
                    var addressDAO = database.addressDao()
                    addressDAO.deleteAll()

                }
            }
        }
    }
    companion object{
        @Volatile
        private var INSTANCE: AddressRoomDatabase? = null

        fun getDatabase(context: Context,
        scope: CoroutineScope): AddressRoomDatabase{
            var migration12 = object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE address_table ADD COLUMN pincode TEXT")
                    database.execSQL("ALTER TABLE address_table ADD COLUMN address TEXT")
                    database.execSQL("ALTER TABLE address_table ADD COLUMN city TEXT")
                    database.execSQL("ALTER TABLE address_table ADD COLUMN state TEXT")

                }
            }
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                AddressRoomDatabase::class.java,
                "address_database")
                    .addMigrations(migration12)
                    .build()
                INSTANCE = instance

                instance
            }
        }

    }
}