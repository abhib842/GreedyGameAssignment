package com.example.fluperdemotask.view.fragment

import android.graphics.BitmapFactory
import android.os.Bundle

import com.example.fluperdemotask.R
import com.example.fluperdemotask.databinding.FullImageViewBinding
import com.example.fluperdemotask.viewmodel.ProductViewModel
import com.example.fluperdemotask.view.binding.BindingFragment
import kotlinx.android.synthetic.main.full_image_view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Class to set image on with full screen
 */
class FullImageFragment : BindingFragment<FullImageViewBinding>() {

    private val mViewModel: ProductViewModel by sharedViewModel()
    override fun getLayoutResId() = R.layout.full_image_view

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.updateProductViewModel = mViewModel
        binding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()
        val img = arguments?.getString("img")
        val imageB = android.util.Base64.decode(img, 0)
        val image = BitmapFactory.decodeByteArray(imageB, 0, imageB.size)
        mFull_image.setImageBitmap(image)
    }

}
