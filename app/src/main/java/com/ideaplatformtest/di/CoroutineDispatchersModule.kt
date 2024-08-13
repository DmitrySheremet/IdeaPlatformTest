package com.ideaplatformtest.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class CoroutineDispatchersModule {
  @Provides
  @CoroutineDispatcherUI
  fun provideDispatcherUI(): CoroutineDispatcher = Dispatchers.Main

  @Provides
  @CoroutineDispatcherIO
  fun provideDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

  @Provides
  @CoroutineDispatcherDefault
  fun provideDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default
}