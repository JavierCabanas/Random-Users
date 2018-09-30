package me.cabanas.javi.randomusers.di.modules

import dagger.Module
import dagger.Provides
import me.cabanas.javi.randomusers.feature.users.repository.UserRepository
import me.cabanas.javi.randomusers.feature.users.repository.UserRepositoryImpl
import me.cabanas.javi.randomusers.feature.users.repository.network.UserNetworkDatasource

@Module
class RepositoryModule {
    @Provides
    fun provideUserRepository(networkDatasource: UserNetworkDatasource): UserRepository =
            UserRepositoryImpl(networkDatasource)

}