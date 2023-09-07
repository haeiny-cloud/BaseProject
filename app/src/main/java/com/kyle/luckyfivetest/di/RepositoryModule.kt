package com.kyle.luckyfivetest.di

import com.kyle.luckyfivetest.domain.repo.ProductRepository
import com.kyle.luckyfivetest.domain.repo.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsProductRepository(impl: ProductRepositoryImpl): ProductRepository
}