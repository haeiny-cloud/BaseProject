package com.kyle.luckyfivetest.di

import com.kyle.luckyfivetest.domain.repo.product.ProductRepository
import com.kyle.luckyfivetest.domain.repo.product.ProductRepositoryImpl
import com.kyle.luckyfivetest.domain.repo.user.UserRepository
import com.kyle.luckyfivetest.domain.repo.user.UserRepositoryImpl
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

    @Binds
    @Singleton
    abstract fun bindsUserRepository(impl: UserRepositoryImpl): UserRepository
}