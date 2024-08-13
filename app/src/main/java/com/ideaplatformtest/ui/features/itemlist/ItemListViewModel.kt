package com.ideaplatformtest.ui.features.itemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ideaplatformtest.domain.Item
import com.ideaplatformtest.domain.repository.ItemsRepository
import com.ideaplatformtest.domain.usecase.DeleteItemUseCase
import com.ideaplatformtest.domain.usecase.SearchItemsUseCase
import com.ideaplatformtest.domain.usecase.UpdateItemUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.openjdk.tools.sjavac.Log
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.util.Date

class ItemListViewModel(
  private val dispatcherUI: CoroutineDispatcher,
  private val dispatcherIO: CoroutineDispatcher,
  private val dispatcherDefault: CoroutineDispatcher,
  private val itemsRepository: ItemsRepository
) : ViewModel(), ContainerHost<ItemListUiState, ItemListUiSideEffect> {
  override val container: Container<ItemListUiState, ItemListUiSideEffect> =
    container(ItemListUiState())
  private var searchItemsJob: Job? = null

  init {
    intent {
      action(ItemListUiAction.SearchItems(state.filterText))
    }
  }

  fun action(action: ItemListUiAction) {
    when (action) {
      is ItemListUiAction.SearchItems -> {
        intent {
          reduce {
            state.copy(filterText = action.text)
          }
        }

        searchItemsJob?.let { job ->
          job.cancel()
        }
        searchItemsJob = viewModelScope.launch(dispatcherIO) {
          try {
            val items = searchItems(action.text)
            if (isActive) withContext(dispatcherUI) {
              intent {
                reduce {
                  state.copy(items = items)
                }
              }
            }
          } catch (e: CancellationException) {
            throw e
          } catch (e: Exception) {
            Log.error(e)
            intent { postSideEffect(ItemListUiSideEffect.ItemListUiSideEffectError()) }
          }

        }
      }

      is ItemListUiAction.EditItem -> {
        intent {
          reduce {
            state.copy(editItem = action.item.copy())
          }
        }
      }

      is ItemListUiAction.EditItemCancel -> {
        intent { reduce { state.copy(editItem = null) } }
      }

      is ItemListUiAction.EditItemAddQuantity -> {

        intent {
          if (state.editItem != null) {
            reduce {
              var newAmount = state.editItem!!.amount + action.quantity
              if (newAmount < 0) newAmount = 0
              state.copy(editItem = state.editItem!!.copy(amount = newAmount))
            }

          }
        }
      }

      ItemListUiAction.CommitItemUpdate -> {
        intent {
          val item = state.editItem
          if (item != null) viewModelScope.launch(dispatcherIO) {
            try {
              if (updateItem(item)) {
                withContext(dispatcherUI) {
                  reduce { state.copy(editItem = null) }
                }
              } else withContext(dispatcherUI) { intent { postSideEffect(ItemListUiSideEffect.ItemListUiSideEffectError()) } }
            } catch (e: CancellationException) {
              throw e
            } catch (e: Exception) {
              Log.error(e)
              withContext(dispatcherUI) { intent { postSideEffect(ItemListUiSideEffect.ItemListUiSideEffectError()) } }
            } finally {
              withContext(dispatcherUI) { intent { action(ItemListUiAction.SearchItems(state.filterText)) } }
            }
          }
        }
      }

      is ItemListUiAction.DeleteItemRequest -> {
        intent {
          reduce {
            state.copy(deleteItem = action.item.copy())
          }
        }

      }

      ItemListUiAction.DeleteItemCancel -> {
        intent { reduce { state.copy(deleteItem = null) } }
      }

      ItemListUiAction.CommitItemDelete -> {
        intent {
          val item = state.deleteItem
          if (item != null) viewModelScope.launch(dispatcherIO) {
            try {
              if (deleteItem(item)) {
                withContext(dispatcherUI) {
                  reduce { state.copy(deleteItem = null) }
                }
              } else withContext(dispatcherUI) { intent { postSideEffect(ItemListUiSideEffect.ItemListUiSideEffectError()) } }
            } catch (e: CancellationException) {
              throw e
            } catch (e: Exception) {
              Log.error(e)
              withContext(dispatcherUI) { intent { postSideEffect(ItemListUiSideEffect.ItemListUiSideEffectError()) } }
            } finally {
              withContext(dispatcherUI) { intent { action(ItemListUiAction.SearchItems(state.filterText)) } }
            }
          }
        }

      }
    }
  }

  private fun searchItems(text: String): List<Item> =
    SearchItemsUseCase(itemsRepository).search(text)

  private fun updateItem(item: Item): Boolean = UpdateItemUseCase(itemsRepository).update(item)

  private fun deleteItem(item: Item): Boolean = DeleteItemUseCase(itemsRepository).delete(item)
}