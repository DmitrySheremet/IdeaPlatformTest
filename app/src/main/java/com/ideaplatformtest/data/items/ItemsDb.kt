package com.ideaplatformtest.data.items

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItemEntity::class], version = 1)
abstract class ItemsDb : RoomDatabase() {
  abstract fun itemsDao(): ItemsDao
}