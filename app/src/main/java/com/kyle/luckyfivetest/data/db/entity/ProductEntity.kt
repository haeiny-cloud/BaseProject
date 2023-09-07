package com.kyle.luckyfivetest.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_product")
data class ProductEntity(
    val imageUrl: String,
    val title: String,
    val price: Int,
    val luckyPrice: Int,

    @PrimaryKey(autoGenerate = true)
    val productId: Int = 0,
)