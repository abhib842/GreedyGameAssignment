package com.example.fluperdemotask.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Stores(@PrimaryKey(autoGenerate = true)
                  @ColumnInfo(name = "id") val id: Int = 0,
                  @ColumnInfo(name = "name") val name:String,
                  @ColumnInfo(name = "address") val address:String
                  ) {

}