package com.kyle.luckyfivetest.ui.main

import com.kyle.luckyfivetest.data.db.entity.ProductEntity
import com.kyle.luckyfivetest.domain.repo.product.ProductRepository
import com.kyle.luckyfivetest.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val productRepository: ProductRepository) : BaseViewModel() {

    suspend fun saveProduct(product: ProductEntity) {
        productRepository.saveProduct(product)
    }

    suspend fun deleteProduct(url: String) {
        productRepository.deleteProduct(url)
    }

    val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
}