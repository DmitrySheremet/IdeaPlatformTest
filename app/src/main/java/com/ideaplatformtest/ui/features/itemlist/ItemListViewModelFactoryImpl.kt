package com.ideaplatformtest.ui.features.itemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.ideaplatformtest.di.CoroutineDispatcherDefault
import com.ideaplatformtest.di.CoroutineDispatcherIO
import com.ideaplatformtest.di.CoroutineDispatcherUI
import com.ideaplatformtest.domain.repository.ItemsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ItemListViewModelFactoryImpl @Inject constructor(
  @CoroutineDispatcherUI private val dispatcherUI: CoroutineDispatcher,
  @CoroutineDispatcherIO private val dispatcherIO: CoroutineDispatcher,
  @CoroutineDispatcherDefault private val dispatcherDefault: CoroutineDispatcher,
  private val itemsRepository: ItemsRepository
) : ItemListViewModelFactory,
  ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
    return ItemListViewModel(
      dispatcherUI = dispatcherUI,
      dispatcherIO = dispatcherIO,
      dispatcherDefault = dispatcherDefault,
      itemsRepository = itemsRepository
    ) as T
  }
}