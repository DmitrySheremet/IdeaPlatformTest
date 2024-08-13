package com.ideaplatformtest.ui.features.itemlist

sealed class ItemListUiSideEffect {
  class ItemListUiSideEffectError(val message: String? = null) : ItemListUiSideEffect()
}