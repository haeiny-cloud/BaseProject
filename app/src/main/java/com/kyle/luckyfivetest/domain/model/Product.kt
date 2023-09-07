package com.kyle.luckyfivetest.domain.model

data class Product(
    val productId: Int,
    val imageUrl: String,
    val title: String,
    val price: Int,
    val luckyPrice: Int,
)
