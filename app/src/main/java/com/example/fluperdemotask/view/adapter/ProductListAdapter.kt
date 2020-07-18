package com.example.fluperdemotask.view.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fluperdemotask.R
import com.example.fluperdemotask.data.database.entity.Products
import com.example.fluperdemotask.databinding.RowItemViewBinding
import java.lang.Exception

/**
 * Adapter Class to set data one by one in each list
 */
class ProductListAdapter(private val list: List<Products>, private val listener: ImageClickedListener
) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(
        view: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val inflater = LayoutInflater.from(view.context)
        val binding: RowItemViewBinding =
            DataBindingUtil.inflate(inflater, R.layout.row_item_view, view, false)
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBind(list[position], listener)
    }


}

/**
 * Inner class to set data on view
 */
class ViewHolder(val binding: RowItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun itemBind(product: Products, clickListener: ImageClickedListener) {
        var photo: String? = null
        binding.mProdName.text = product.name
        binding.mProdDescription.text = product.description
        binding.mProdRegPrice.text = product.regular_price
        binding.mProdSalePrice.text = product.sale_price
        binding.mProdColor.text = product.colors?.get(0)
        binding.mProdStores.text = product.stores?.get(0)?.name
        photo = product.product_photo
        try {
            val im = android.util.Base64.decode(photo, 0)
            val image_factory = BitmapFactory.decodeByteArray(im, 0, im.size)
            binding.mImage.setImageBitmap(image_factory)
        }catch (e:Exception){
            e.printStackTrace()
        }


        binding.mItem.setOnClickListener {
            clickListener.onLayoutClick(product)

        }

        binding.mImage.setOnClickListener {
            if (photo != null) {
                clickListener.onImageClick(photo)
            }
        }
    }

}


/**
 * Interface to parse data to another class
 */
interface ImageClickedListener {
    fun onImageClick(stringImage: String)
    fun onLayoutClick(product: Products)
}

