package com.example.fluperdemotask.module

import com.example.fluperdemotask.data.database.AppDatabase
import com.example.fluperdemotask.data.database.Repositories
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val roomModule = module {
    single { AppDatabase.getInstance(androidApplication()) }
    single(createOnStart = false) { get<AppDatabase>().getProductsDao() }
    single { Repositories(get()) }

}


