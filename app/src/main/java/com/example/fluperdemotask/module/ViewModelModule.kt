package com.example.fluperdemotask.module

import com.example.fluperdemotask.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { ProductViewModel(get()) }
}