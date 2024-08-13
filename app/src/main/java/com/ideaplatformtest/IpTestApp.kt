package com.ideaplatformtest

import android.app.Application
import android.content.Context
import com.ideaplatformtest.di.AppComponent
import com.ideaplatformtest.di.DaggerAppComponent

class IpTestApp : Application() {
  companion object {
    private lateinit var _appComponent: AppComponent
    val appComponent get() = _appComponent
    private lateinit var _applicationContext: Context
    val applicationContext get() = _applicationContext
  }

  override fun onCreate() {
    super.onCreate()
    _applicationContext = this.applicationContext
    _appComponent = DaggerAppComponent.factory().create(this)
  }
}