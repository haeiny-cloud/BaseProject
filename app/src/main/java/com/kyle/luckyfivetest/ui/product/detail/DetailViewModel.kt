package com.kyle.luckyfivetest.ui.product.detail

import androidx.lifecycle.MutableLiveData
import com.kyle.luckyfivetest.data.db.entity.ProductEntity
import com.kyle.luckyfivetest.domain.repo.ProductRepository
import com.kyle.luckyfivetest.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    var product: MutableLiveData<ProductEntity> = MutableLiveData()

    suspend fun getProduct(index: Int): ProductEntity {
        return productRepository.getProduct(index)
    }
}