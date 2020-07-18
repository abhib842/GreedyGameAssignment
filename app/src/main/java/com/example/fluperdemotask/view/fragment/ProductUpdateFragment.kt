package com.example.fluperdemotask.view.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import com.example.fluperdemotask.data.database.entity.Stores
import com.example.fluperdemotask.R
import com.example.fluperdemotask.data.database.entity.Products
import com.example.fluperdemotask.databinding.UpdateDataBinding
import com.example.fluperdemotask.viewmodel.ProductViewModel
import com.example.fluperdemotask.view.binding.BindingFragment
import kotlinx.android.synthetic.main.update_data.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Class to update and delete  product from data base
 */
class ProductUpdateFragment : BindingFragment<UpdateDataBinding>(),View.OnClickListener {
    private var productID:Int = 0
    private var productImage:String? = null
    private  val updateProductViewModel: ProductViewModel by sharedViewModel()
    override fun getLayoutResId() = R.layout.update_data

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.updateProductViewModel = updateProductViewModel
        binding.lifecycleOwner = this
        ivDisplayProductImage.setOnClickListener(this)
        product_update.setOnClickListener(this)
        product_delete.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        val isCreateProduct = arguments?.getBoolean("create_p")
        if(isCreateProduct!!){
            deteltP()
            llUpdateDeleteProductItem.visibility = GONE
            btSaveProduct.visibility = VISIBLE
            ivDisplayProductImage.visibility =GONE

        }else {
            displayProductData()
            llUpdateDeleteProductItem.visibility = VISIBLE
            btSaveProduct.visibility = GONE
            ivDisplayProductImage.visibility =VISIBLE
        }
    }



    /**
     * Method to display data for update
     */
    private fun displayProductData(){
      updateProductViewModel.selected.observe(viewLifecycleOwner, Observer {
          if(it != null) {
              setProductData(it)
          }
      })
    }

    /**
     * Method to set data on text view
     */
    private fun setProductData(product: Products){
        productID = product.id
        etProductName.setText(product.name)
        etProductDescription.setText(product.description)
        etProductRegPrice.setText(product.regular_price)
        etProductSalePrice.setText(product.sale_price)
        etProductColor.setText(product.colors?.get(0))
        etProductStores.setText(product.stores?.get(0)?.name)
        etProductStoreAddress.setText(product.stores?.get(0)?.address)
        productImage = product.product_photo
        try {
            val imageBytes = android.util.Base64.decode(product.product_photo, 0)
            val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            ivDisplayProductImage.setImageBitmap(image)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    /**
     * Method to Delete data from UI
     */
    private fun deteltP(){
        etProductName.text.clear()
        etProductDescription.text.clear()
        etProductRegPrice.text.clear()
        etProductSalePrice.text.clear()
        etProductColor.text.clear()
        etProductStores.text.clear()
        etProductStoreAddress.text.clear()
        ivDisplayProductImage.setImageBitmap(null)
    }

    override fun onClick(v: View?) {
        if(v != null) when (v.id) {
             R.id.product_update -> updateProductDetails()
            R.id.product_delete -> deleteProductDetails()
        }
    }

    /**
     * Method to Update product Data
     */
    private fun updateProductDetails(){
        val storeList: MutableList<Stores>? = mutableListOf<Stores>()
        storeList?.add(0,
            Stores(
                0,
                etProductStores.text.toString(),
                etProductStoreAddress.text.toString()
            )
            )

        val colorList: MutableList<String>? = mutableListOf<String>()
        colorList?.add(0,etProductColor.text.toString())

        val product:Products = Products(productID,
            etProductName.text.toString(),
            etProductDescription.text.toString(),
            etProductRegPrice.text.toString(),
            etProductSalePrice.text.toString(),
            productImage,
            colorList,
            storeList)
        updateProductViewModel.updateProduct(product)
    }

    /**
     * Method to Delete product Data
     */
    private fun deleteProductDetails(){
        val storeList: MutableList<Stores>? = null
        storeList?.apply {
            add(0,
                Stores(
                    0,
                    etProductStores.text.toString(),
                    etProductStoreAddress.text.toString()
                )
            ) }

        val colorList: MutableList<String>? = null
        colorList?.apply {
            add(0,etProductColor.text.toString()) }
        val product:Products = Products(productID,
            etProductName.text.toString(),
            etProductDescription.text.toString(),
            etProductRegPrice.text.toString(),
            etProductSalePrice.text.toString(),
            productImage,
            colorList,
            storeList)
        updateProductViewModel.deleteProduct(product)
    }





}
