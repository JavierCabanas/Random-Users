package me.cabanas.javi.randomusers.di.modules

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import me.cabanas.javi.randomusers.features.users.presentation.UserDetailActivity
import me.cabanas.javi.randomusers.features.users.presentation.UserDetailViewModel
import me.cabanas.javi.randomusers.features.users.presentation.UsersActivity
import me.cabanas.javi.randomusers.features.users.presentation.UsersAdapter

@Module
abstract class UserDetailViewBindingModule {
    @ContributesAndroidInjector
    abstract fun userDetailActivity(): UserDetailActivity

}