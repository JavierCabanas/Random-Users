package me.cabanas.javi.randomusers.features.users.repository

import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.features.users.domain.model.UserEntity
import me.cabanas.javi.randomusers.features.users.repository.local.UserLocalDataSource
import me.cabanas.javi.randomusers.features.users.repository.network.UserNetworkDataSource

class UserRepositoryImpl(val network: UserNetworkDataSource,
                         val local: UserLocalDataSource) : UserRepository {
    override fun readUserList(request: ReadUserListInteractor.UserListRequest):
            Either<Failure, List<UserEntity>> {

        val result = local.readUserList(request)
        return if (result.isLeft) {
            readFromNetwork(request)
        } else
            result
    }

    private fun readFromNetwork(request: ReadUserListInteractor.UserListRequest):
            Either<Failure, List<UserEntity>> {
        val result = network.readUserList(request)
        result.either({}, { local.writeContactList(it) })
        return network.readUserList(request)
    }
}