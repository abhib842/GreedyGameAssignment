package com.example.fluperdemotask.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fluperdemotask.R
import com.example.fluperdemotask.data.database.entity.Products
import com.example.fluperdemotask.databinding.FragmentProductListViewBinding
import com.example.fluperdemotask.view.adapter.ImageClickedListener
import com.example.fluperdemotask.view.adapter.ProductListAdapter
import com.example.fluperdemotask.viewmodel.ProductViewModel
import com.example.fluperdemotask.view.binding.BindingFragment
import kotlinx.android.synthetic.main.fragment_product_list_view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Class to show product data in list view
 */
class ProductFragment : BindingFragment<FragmentProductListViewBinding>(),
    ImageClickedListener,View.OnClickListener{
    override fun getLayoutResId() = R.layout.fragment_product_list_view
    private  val mViewModel: ProductViewModel by sharedViewModel()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.productListViewModel = mViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        create_product.setOnClickListener(this)
    }


    private fun initRecyclerView(){
        binding.rvProductList.layoutManager = LinearLayoutManager(context)
        displayProductList()
    }

    @SuppressLint("FragmentLiveDataObserve")
    fun displayProductList(){
        mViewModel.products.observe(this, Observer {
        binding.rvProductList.adapter =
                ProductListAdapter(
                    it,
                    this
                )

        })
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun productListItemClicked(product: Products){

        mViewModel.selectedProduct(product)
        val createProduct = Bundle().apply {
            putBoolean("create_p", false)
        }
        activity?.findNavController(R.id.nav_host_fragment)?.navigate( R.id.action_ProductListFragment_to_UpdateProductFragment,createProduct)

    }

    override fun onImageClick(stringImage: String) {
        val createProduct = Bundle().apply {
            putString("img", stringImage)
        }
        activity?.findNavController(R.id.nav_host_fragment)?.navigate( R.id.action_ProductListFragment_to_FullScreenImage,createProduct)

    }

    override fun onLayoutClick(product: Products) {
        mViewModel.selectedProduct(product)
        val createProduct = Bundle().apply {
            putBoolean("create_p", false)
        }
        activity?.findNavController(R.id.nav_host_fragment)?.navigate( R.id.action_ProductListFragment_to_UpdateProductFragment,createProduct)


    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.create_product -> {
                val createProduct = Bundle().apply {
                    putBoolean("create_p", true)
                }
                activity?.findNavController(R.id.nav_host_fragment)?.navigate( R.id.action_ProductListFragment_to_UpdateProductFragment,createProduct)
            }
        }
    }

}
