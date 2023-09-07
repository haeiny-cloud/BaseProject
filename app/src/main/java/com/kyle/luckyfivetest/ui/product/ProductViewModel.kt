package com.kyle.luckyfivetest.ui.product

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kyle.luckyfivetest.data.db.entity.ProductEntity
import com.kyle.luckyfivetest.domain.repo.ProductRepository
import com.kyle.luckyfivetest.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    val products: StateFlow<PagingData<ProductEntity>> =
        productRepository.getProducts()
            .cachedIn(viewModelScope) // 코루틴이 데이터 흐름을 캐시하고 공유 가능하게 만든다.
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty())

    suspend fun saveProduct(product: ProductEntity) {
        productRepository.saveProduct(product)
    }

}