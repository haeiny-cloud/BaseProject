package com.kyle.luckyfivetest.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kyle.luckyfivetest.data.db.entity.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM table_product")
    fun getAll(): PagingSource<Int, ProductEntity>

    @Query("SELECT * FROM table_product WHERE productId = :productId")
    fun getProduct(productId: Int): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProduct(product: ProductEntity)

    @Query("UPDATE table_product SET imageUrl = :url")
    fun deleteProduct(url: String)
}