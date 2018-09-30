package me.cabanas.javi.randomusers.di.modules

import dagger.Module
import dagger.Provides
import me.cabanas.javi.randomusers.features.users.repository.UserRepository
import me.cabanas.javi.randomusers.features.users.repository.UserRepositoryImpl
import me.cabanas.javi.randomusers.features.users.repository.local.UserLocalDataSource
import me.cabanas.javi.randomusers.features.users.repository.network.UserNetworkDataSource

@Module
class RepositoryModule {
    @Provides
    fun provideUserRepository(networkDataSource: UserNetworkDataSource,
                              localDataSource: UserLocalDataSource): UserRepository =
            UserRepositoryImpl(networkDataSource, localDataSource)

}