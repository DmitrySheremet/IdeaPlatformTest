package com.ideaplatformtest.ui.features.itemlist

import com.ideaplatformtest.domain.Item

sealed class ItemListUiAction {
  class SearchItems(val text: String) : ItemListUiAction()
  class EditItem(val item: Item): ItemListUiAction()
  object EditItemCancel : ItemListUiAction()
  class EditItemAddQuantity(val quantity: Long): ItemListUiAction()
  object CommitItemUpdate: ItemListUiAction()
  class DeleteItemRequest(val item: Item): ItemListUiAction()
  object DeleteItemCancel: ItemListUiAction()
  object CommitItemDelete: ItemListUiAction()
}