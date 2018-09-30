package me.cabanas.javi.randomusers.di.components

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.cabanas.javi.randomusers.di.modules.ApplicationModule
import me.cabanas.javi.randomusers.di.modules.UserListViewBindingModule
import me.cabanas.javi.randomusers.di.modules.ViewModelModule
import me.cabanas.javi.randomusers.framework.App
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class,
    ViewModelModule::class, UserListViewBindingModule::class])
interface ApplicationComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): ApplicationComponent.Builder

        fun build(): ApplicationComponent
    }
}