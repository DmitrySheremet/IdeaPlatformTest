package com.ideaplatformtest.domain.repository

import com.ideaplatformtest.domain.Item

interface ItemsRepository {
  fun getItems(filter: String): List<Item>
  fun updateItem(item: Item): Boolean
  fun deleteItem(item: Item): Boolean
}