package me.cabanas.javi.randomusers.di.modules

import dagger.Module
import dagger.Provides
import me.cabanas.javi.randomusers.features.users.repository.local.RealmUsersDataSource
import me.cabanas.javi.randomusers.features.users.repository.local.UserLocalDataSource
import me.cabanas.javi.randomusers.features.users.repository.network.RetroftiUsersClient
import me.cabanas.javi.randomusers.features.users.repository.network.UserNetworkDataSource

@Module
class LocalStorageDataSourceModule {
    @Provides
    fun provideUserLocalDatasource(): UserLocalDataSource = RealmUsersDataSource()

}