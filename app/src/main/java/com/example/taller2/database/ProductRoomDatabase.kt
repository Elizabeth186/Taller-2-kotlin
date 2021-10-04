package com.example.taller2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taller2.dao.ProductDao
import com.example.taller2.entities.Product


@Database(entities = [Product::class], version = 1, exportSchema = false)
 abstract class ProductRoomDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    companion object {

        @Volatile
        private var INSTANCE: ProductRoomDatabase? = null
        fun getDatabase(context: Context): ProductRoomDatabase{

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductRoomDatabase::class.java,
                    "vehicle_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }}