package com.example.apicatsa.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class Cat(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
)