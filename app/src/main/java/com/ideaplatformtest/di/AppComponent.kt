package com.ideaplatformtest.di

import android.content.Context
import com.ideaplatformtest.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, CoroutineDispatchersModule::class, DbModule::class, RepositoryModule::class])
@Singleton
interface AppComponent {
  @Component.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): AppComponent
  }

  fun inject(activity: MainActivity)
}