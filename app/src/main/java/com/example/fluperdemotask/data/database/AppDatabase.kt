package com.example.fluperdemotask.data.database

import android.content.Context
import androidx.room.*
import com.example.fluperdemotask.data.database.converter.ColorsConverter
import com.example.fluperdemotask.data.database.converter.StoresConverters
import com.example.fluperdemotask.data.database.AppDatabase.Companion.DB_VERSION
import com.example.fluperdemotask.data.database.entity.Products

// Annotates class to be a Room Database with a table (entity) of the Product class
@Database(entities = [Products::class], version = DB_VERSION, exportSchema = false)
@TypeConverters(StoresConverters::class, ColorsConverter::class)
abstract class AppDatabase :RoomDatabase(){
    abstract fun getProductsDao(): Dao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "fluperDemoDb"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context?): AppDatabase{
            synchronized(this){
                var instance:AppDatabase?= INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context!!.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME
                    ).build()
                }
               return instance
            }

        }
    }

}