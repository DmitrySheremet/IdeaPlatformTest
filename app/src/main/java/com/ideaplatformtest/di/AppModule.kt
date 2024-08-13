package com.ideaplatformtest.di

import com.ideaplatformtest.ui.features.itemlist.ItemListViewModelFactory
import com.ideaplatformtest.ui.features.itemlist.ItemListViewModelFactoryImpl
import dagger.Binds
import dagger.Module

@Module
interface AppModule {
  @Binds
  fun provideItemListViewModelFactory(factory: ItemListViewModelFactoryImpl): ItemListViewModelFactory
}