package me.cabanas.javi.randomusers.di.modules

import dagger.Module
import dagger.Provides
import me.cabanas.javi.randomusers.features.users.repository.network.RetrofitUsersClient
import me.cabanas.javi.randomusers.features.users.repository.network.UserNetworkDataSource

@Module
class NetworkDataSourceModule {
    @Provides
    fun provideUserNetworkDatasource(): UserNetworkDataSource = RetrofitUsersClient()

}