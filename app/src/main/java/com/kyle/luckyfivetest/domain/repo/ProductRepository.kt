package com.kyle.luckyfivetest.domain.repo

import androidx.paging.PagingData
import com.kyle.luckyfivetest.data.db.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<PagingData<ProductEntity>>
    suspend fun getProduct(productId: Int): ProductEntity
    suspend fun saveProduct(product: ProductEntity)
    suspend fun deleteProduct(url: String)
}