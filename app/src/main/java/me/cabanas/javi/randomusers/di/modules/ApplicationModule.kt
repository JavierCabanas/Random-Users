package me.cabanas.javi.randomusers.di.modules


import dagger.Module
import me.cabanas.javi.randomusers.framework.App

@Module(includes = [NetworkDatasourceModule::class])
abstract class ApplicationModule(private val application: App)