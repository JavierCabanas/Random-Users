package me.cabanas.javi.randomusers.di.modules

import dagger.Module
import dagger.Provides
import me.cabanas.javi.randomusers.features.users.repository.local.RealmUsersDataSource
import me.cabanas.javi.randomusers.features.users.repository.local.UserLocalDataSource

@Module
class LocalStorageDataSourceModule {
    @Provides
    fun provideUserLocalDatasource(): UserLocalDataSource = RealmUsersDataSource()

}