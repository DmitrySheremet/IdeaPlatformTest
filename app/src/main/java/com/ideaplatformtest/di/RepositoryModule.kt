package com.ideaplatformtest.di

import com.ideaplatformtest.data.items.ItemsDbRepository
import com.ideaplatformtest.domain.repository.ItemsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
  @Binds
  fun provideItemsRepository(repository: ItemsDbRepository): ItemsRepository
}