package com.ideaplatformtest.data.items

import com.ideaplatformtest.data.mappers.toItem
import com.ideaplatformtest.data.mappers.toItemEntity
import com.ideaplatformtest.domain.Item
import com.ideaplatformtest.domain.repository.ItemsRepository
import javax.inject.Inject

class ItemsDbRepository @Inject constructor(private val itemsDb: ItemsDb) : ItemsRepository {
  override fun getItems(filter: String): List<Item> {
    return itemsDb.itemsDao().searchItems(filter).map { entity -> entity.toItem() }
  }

  override fun updateItem(item: Item): Boolean {
    return itemsDb.itemsDao().updateItem(item.toItemEntity()) > 0
  }

  override fun deleteItem(item: Item): Boolean {
    return itemsDb.itemsDao().deleteItem(item.toItemEntity()) > 0
  }
}