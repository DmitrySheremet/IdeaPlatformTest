package com.ideaplatformtest.domain.usecase

import com.ideaplatformtest.domain.Item
import com.ideaplatformtest.domain.repository.ItemsRepository

class UpdateItemUseCase(private val itemsRepository: ItemsRepository) {
  fun update(item: Item): Boolean = itemsRepository.updateItem(item)
}