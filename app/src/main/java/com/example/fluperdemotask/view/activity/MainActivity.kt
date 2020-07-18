package com.example.fluperdemotask.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fluperdemotask.R
import com.example.fluperdemotask.databinding.ActivityMainBinding
import com.example.fluperdemotask.viewmodel.ProductViewModel
import com.example.fluperdemotask.view.binding.BindingActivity
import kotlinx.android.synthetic.main.custom_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BindingActivity<ActivityMainBinding>() {
    private  val mViewModel: ProductViewModel by viewModel()
    private lateinit var navigationController: NavController
    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainViewModel = mViewModel
        binding.lifecycleOwner = this
        setSupportActionBar(toolbar)
        navigationController = findNavController(R.id.nav_host_fragment)
        showAllProduct()
    }

    /**
     * Method to show all in list view using live data
     */
    private fun showAllProduct(){
        mViewModel.products.observe(this, Observer {
            if(it.isEmpty()) {
                val data = mViewModel.getJson(applicationContext)
                if (data != null) {
                    mViewModel.saveDataInfoLocalDataBase(data)
                }
            }else{
                val host = NavHostFragment.create(R.navigation.navigtion)
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, host)
                    .setPrimaryNavigationFragment(host).commit()
            }
        })

    }

}
