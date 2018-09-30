package me.cabanas.javi.randomusers.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import me.cabanas.javi.randomusers.feature.users.presentation.UsersViewModel
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
class ViewModelModule {
    @MapKey
    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Singleton
    @Suppress("UNCHECKED_CAST")
    @Provides
    fun provideViewModelFactory(providers: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return requireNotNull(providers[modelClass]).get() as T
        }
    }

    @Provides
    @IntoMap
    @ViewModelModule.ViewModelKey(UsersViewModel::class)
    fun provideContactsViewModel(): ViewModel = UsersViewModel()
}
