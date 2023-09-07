package com.kyle.luckyfivetest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kyle.luckyfivetest.data.db.dao.ProductDao
import com.kyle.luckyfivetest.data.db.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}