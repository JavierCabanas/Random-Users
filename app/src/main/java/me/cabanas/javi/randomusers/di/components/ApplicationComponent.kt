package me.cabanas.javi.randomusers.di.components

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.cabanas.javi.randomusers.di.modules.*
import me.cabanas.javi.randomusers.di.modules.interactors.UserModule
import me.cabanas.javi.randomusers.framework.App
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class,
    RepositoryModule::class, NetworkDataSourceModule::class, LocalStorageDataSourceModule::class,
    ViewModelModule::class, UserModule::class, UserListViewBindingModule::class])
interface ApplicationComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): ApplicationComponent.Builder

        fun build(): ApplicationComponent
    }
}