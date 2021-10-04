package com.example.taller2.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taller2.entities.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM product_table ORDER BY name ASC")
    fun getAlphabetizedProducts(): Flow<List<Product>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)
    @Query("DELETE FROM  product_table WHERE id=:id")
    suspend fun deleteOneItem(id:Int)


}