package com.ideaplatformtest.di

import android.content.Context
import androidx.room.Room
import com.ideaplatformtest.data.items.ItemsDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
  @Provides
  @Singleton
  fun provideItemsDatabase(context: Context): ItemsDb =
    Room.databaseBuilder(context = context, klass = ItemsDb::class.java, name = "iptest.db")
      .createFromAsset("data.db").build()

}