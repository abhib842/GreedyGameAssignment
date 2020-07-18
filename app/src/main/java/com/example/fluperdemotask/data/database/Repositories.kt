package com.example.fluperdemotask.data.database

import com.example.fluperdemotask.data.database.entity.Products


/**
 * This class is used to deal with  perform CRUD operations in local  database
 */
class Repositories(private val dao: Dao) {

    val products = dao.getAllProducts()


    suspend fun insetProducts(product:Products){
        dao.insertProduct(product)
    }


    suspend fun updateProducts(product: Products){
        dao.updateProducts(product)
    }

    suspend fun deleteProducts(product: Products){
        dao.deleteProducts(product)
    }


    suspend fun insertProductListFromJson(productList:List<Products>){
        dao.insertProductListFromJson(productList)
    }
}