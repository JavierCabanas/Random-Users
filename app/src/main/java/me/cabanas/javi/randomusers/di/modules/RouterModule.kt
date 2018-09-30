package me.cabanas.javi.randomusers.di.modules

import dagger.Module
import dagger.Provides
import me.cabanas.javi.randomusers.core.Router
import me.cabanas.javi.randomusers.framework.AndroidRouter

@Module
class RouterModule {
    @Provides
    fun provideRouter(): Router = AndroidRouter()
}