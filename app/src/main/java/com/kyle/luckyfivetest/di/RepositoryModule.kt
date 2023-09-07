package com.kyle.luckyfivetest.di

import com.kyle.luckyfivetest.data.db.dao.ProductDao
import com.kyle.luckyfivetest.domain.repo.ProductRepository
import com.kyle.luckyfivetest.domain.repo.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesProductRepository(dao: ProductDao): ProductRepository {
        return ProductRepositoryImpl(dao)
    }
}