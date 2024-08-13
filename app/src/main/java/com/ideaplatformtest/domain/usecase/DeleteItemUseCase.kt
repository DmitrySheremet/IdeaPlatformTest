package com.ideaplatformtest.domain.usecase

import com.ideaplatformtest.domain.Item
import com.ideaplatformtest.domain.repository.ItemsRepository

class DeleteItemUseCase(private val itemsRepository: ItemsRepository) {
  fun delete(item: Item): Boolean = itemsRepository.deleteItem(item)
}