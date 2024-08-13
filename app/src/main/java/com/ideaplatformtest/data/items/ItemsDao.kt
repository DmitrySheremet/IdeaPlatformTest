package com.ideaplatformtest.data.items

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemsDao {
  @Query(value = "select * from item where name like '%' || :text || '%' order by time")
  fun searchItems(text: String): List<ItemEntity>

  @Update
  fun updateItem(item: ItemEntity): Int

  @Delete
  fun deleteItem(item: ItemEntity): Int
}