package me.cabanas.javi.randomusers.di.modules

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import me.cabanas.javi.randomusers.feature.users.presentation.UsersActivity
import me.cabanas.javi.randomusers.feature.users.presentation.UsersAdapter

@Module
abstract class UserListViewBindingModule {
    @ContributesAndroidInjector(modules = [UserListViewModule::class, RouterModule::class])
    abstract fun UsersActivity(): UsersActivity

    @Module
    class UserListViewModule {
        @Provides
        fun provideUsersAdapter(): UsersAdapter = UsersAdapter()

    }
}