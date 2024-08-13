package com.ideaplatformtest.domain.usecase

import com.ideaplatformtest.domain.Item
import com.ideaplatformtest.domain.repository.ItemsRepository

class SearchItemsUseCase(private val itemsRepository: ItemsRepository) {
  fun search(searchText: String): List<Item> {
    return itemsRepository.getItems(searchText)
  }
}