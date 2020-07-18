package com.example.fluperdemotask.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.fluperdemotask.data.database.entity.Products


/**
 * This Interface is used for the insert update and get data
 */
@Dao
interface Dao {

    @Query("SELECT * FROM Products")
    fun getAllProducts():LiveData<List<Products>>;

    @Insert
    suspend fun insertProduct(product: Products)

    @Insert
    suspend fun insertProductListFromJson(productList: List<Products>)

    @Delete
    suspend fun deleteProducts(product: Products)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProducts(product: Products)


}