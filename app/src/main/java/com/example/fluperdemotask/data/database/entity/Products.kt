package com.example.fluperdemotask.data.database.entity

import androidx.room.*
import com.example.fluperdemotask.data.database.converter.ColorsConverter
import com.example.fluperdemotask.data.database.converter.StoresConverters

@Entity(tableName = "Products")
data class Products(@PrimaryKey(autoGenerate = true)
                    @ColumnInfo(name = "id") val id: Int = 0,
                    @ColumnInfo(name = "name") var name: String,
                    @ColumnInfo(name = "description") var description: String?,
                    @ColumnInfo(name = "regular_price") var regular_price: String?,
                    @ColumnInfo(name = "sale_price") var sale_price: String?,
                    @ColumnInfo(name = "product_photo") var product_photo: String?,
                    @TypeConverters(ColorsConverter::class)
                    @ColumnInfo(name = "colors") var colors: List<String>?,
                    @TypeConverters(StoresConverters::class)
                    @ColumnInfo(name = "stores") var stores: List<Stores>?
)