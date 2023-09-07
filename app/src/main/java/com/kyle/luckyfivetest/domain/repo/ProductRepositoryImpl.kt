package com.kyle.luckyfivetest.domain.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kyle.luckyfivetest.data.db.dao.ProductDao
import com.kyle.luckyfivetest.data.db.entity.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val dao: ProductDao) : ProductRepository {

    override fun getProducts(): Flow<PagingData<ProductEntity>> {
        val pagingSourceFactory = {
            dao.getAll()
        }

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false, // true 라면 전체 데이터사이즈를 미리 받아와서 RecyclerView 에 미리 홀더를 만들어 놓고 나머지를 Null 로 만든다.
                maxSize = 10 * 3 // Pager 가 메모리에 최대로 가지고 있을 수 있는 항목의 개수
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun getProduct(productId: Int): ProductEntity {
        return withContext(Dispatchers.IO) {
            dao.getProduct(productId)
        }
    }

    override suspend fun saveProduct(product: ProductEntity) {
        withContext(Dispatchers.IO) {
            dao.saveProduct(product)
        }
    }

    override suspend fun deleteProduct(url: String) {
        withContext(Dispatchers.IO) {
            dao.deleteProduct(url)
        }
    }

}