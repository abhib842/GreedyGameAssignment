package com.example.fluperdemotask.viewmodel

import android.content.Context
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fluperdemotask.data.database.entity.Stores
import com.example.fluperdemotask.data.database.entity.Products
import com.example.fluperdemotask.data.database.Repositories
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class ProductViewModel(private val repository: Repositories) : ViewModel(), Observable {


    val mProdName = MutableLiveData<String>()
    val mDesc = MutableLiveData<String>()
    val prodRegPrice = MutableLiveData<String>()
    val prodSalePrice = MutableLiveData<String>()
    val prodImage = MutableLiveData<String>()
    val prodColor = MutableLiveData<String>()
    val prodStores = MutableLiveData<String>()
    val prodStoresAddress = MutableLiveData<String>()
    private val statusMessage = MutableLiveData<String>()
    val products = repository.products
    private val _selected = MutableLiveData<Products>()
    var selected : LiveData<Products> = _selected



    /**
     * Method to get data from json
     */
    fun getJson(context: Context): String? {
        val json: String
        try {
            val inputStream = context.getAssets().open("data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.use { it.read(buffer) }
            json = String(buffer)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return json
    }


    /**
     * Method to save data in data base
     */
    fun saveDataInfoLocalDataBase(data:String) {
        saveProduct(convertInJson(data))
    }

    /**
     * Method to insert product
     */
    private fun insertProduct(product: Products): Job =
        viewModelScope.launch {
            repository.insetProducts(product)
            statusMessage.value = "Product Saved Successfully"
        }


    /**
     * Method to save product
     */
    fun updateProduct(product: Products): Job = viewModelScope.launch {
        repository.updateProducts(product)
    }

    /**
     * Method to delete product
     */
    fun deleteProduct(product: Products): Job = viewModelScope.launch {
        repository.deleteProducts(product)
        statusMessage.value = "Product Deleted Successfully"
    }


    private fun convertInJson(value: String): List<Products> {
        val listType = object : TypeToken<List<Products>>() {}.type
        return Gson().fromJson(value, listType)
    }

    /**
     * Method to convert in josn
     */

    private fun saveProduct(productList:List<Products>): Job =
        viewModelScope.launch {
            repository.insertProductListFromJson(productList)
        }

    /**
     * Method to select item
     */

    fun selectedProduct(item: Products) {
        _selected.value = item
    }

    /**
     * Method to save dafa
     */
    fun save(){
        val pName:String = mProdName.value!!
        val pDesc:String = mDesc.value!!
        val pRegPr:String = prodRegPrice.value!!
        val pSalePr:String = prodSalePrice.value!!
        val pImage:String? = prodImage.value
        val pColor:String = prodColor.value!!
        val pStores:String = prodStores.value!!
        val pStoreAddress:String = prodStoresAddress.value!!

        val storeList: MutableList<Stores>? = mutableListOf<Stores>()
        storeList?.add(0,
            Stores(
                0,
                pStores,
                pStoreAddress
            )
        )

        val colorList: MutableList<String>? = mutableListOf<String>()
        colorList?.add(0,pColor)

        insertProduct(Products(0,pName,pDesc,pRegPr,pSalePr,pImage,colorList,storeList))

        mProdName.value = null
        mDesc.value = null
        prodRegPrice.value = null
        prodSalePrice.value = null
        prodImage.value = null
        prodColor.value = null
        prodStores.value = null
        prodStoresAddress.value = null
    }



    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}