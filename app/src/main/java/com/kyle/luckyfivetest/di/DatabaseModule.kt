package com.kyle.luckyfivetest.di

import android.content.Context
import androidx.room.Room
import com.kyle.luckyfivetest.data.db.ProductDatabase
import com.kyle.luckyfivetest.data.db.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesProductDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ProductDatabase::class.java,
            "product.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesTaskDao(database: ProductDatabase): ProductDao {
        return database.productDao()
    }
}