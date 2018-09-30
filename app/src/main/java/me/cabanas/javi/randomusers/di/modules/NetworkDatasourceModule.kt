package me.cabanas.javi.randomusers.di.modules

import dagger.Module
import dagger.Provides
import me.cabanas.javi.randomusers.feature.users.repository.network.RetroftiUsersClient
import me.cabanas.javi.randomusers.feature.users.repository.network.UserNetworkDatasource

@Module
class NetworkDatasourceModule {
    @Provides
    fun provideUserNetworkDatasource(): UserNetworkDatasource = RetroftiUsersClient()

}