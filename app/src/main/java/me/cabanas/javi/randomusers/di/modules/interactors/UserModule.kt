package me.cabanas.javi.randomusers.di.modules.interactors

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.android.UI
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.features.users.repository.UserRepository

@Module
class UserModule {
    @Provides
    fun provideReadUserListInteractor(repository: UserRepository) =
            ReadUserListInteractor(repository, UI)
}