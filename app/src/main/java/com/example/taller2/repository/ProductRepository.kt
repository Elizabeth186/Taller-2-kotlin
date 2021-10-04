package com.example.taller2.repository

import android.content.Context
import com.example.taller2.dao.ProductDao
import com.example.taller2.database.ProductRoomDatabase
import com.example.taller2.entities.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao)  {
    companion object {
        private var INSTANCE : ProductRepository? = null
        fun getRepository(context: Context) : ProductRepository {
            return INSTANCE ?: synchronized(this) {
                val database = ProductRoomDatabase.getDatabase(context)
                val instance = ProductRepository(database.productDao())
                INSTANCE = instance
                instance
            }
        }
    }

    val allProduct: Flow<List<Product>> = productDao.getAlphabetizedProducts()

    suspend fun insert(product:Product) {
        productDao.insert(product)
    }
    suspend fun deleteOneItem(Id:Int) {
        productDao.deleteOneItem(Id)
    }

}