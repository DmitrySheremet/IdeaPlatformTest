package com.ideaplatformtest.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoroutineDispatcherUI

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoroutineDispatcherIO

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoroutineDispatcherDefault