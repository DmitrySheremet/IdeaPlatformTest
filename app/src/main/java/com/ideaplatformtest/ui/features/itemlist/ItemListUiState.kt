package com.ideaplatformtest.ui.features.itemlist

import com.ideaplatformtest.domain.Item

data class ItemListUiState(
  val filterText: String = "",
  val items: List<Item> = listOf(),
  val editItem: Item? = null,
  val deleteItem: Item? = null
)