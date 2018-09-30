package me.cabanas.javi.randomusers.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.cabanas.javi.randomusers.feature.users.presentation.UsersActivity

@Module
abstract class UserListViewBindingModule {
    @ContributesAndroidInjector
    abstract fun UsersActivity(): UsersActivity
}