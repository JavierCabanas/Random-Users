package me.cabanas.javi.randomusers.features.users.repository

import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserInteractor
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.features.users.domain.model.UserEntity
import me.cabanas.javi.randomusers.features.users.repository.local.UserLocalDataSource
import me.cabanas.javi.randomusers.features.users.repository.network.UserNetworkDataSource

class UserRepositoryImpl(val network: UserNetworkDataSource,
                         val local: UserLocalDataSource) : UserRepository {
    override fun readUserList(request: ReadUserListInteractor.Request):
            Either<Failure, List<UserEntity>> {

        val result = local.readUserList(request)
        return when {
            result.isLeft -> readListFromNetwork(request)
            else -> result
        }
    }

    private fun readListFromNetwork(request: ReadUserListInteractor.Request):
            Either<Failure, List<UserEntity>> {
        val result = network.readUserList(request)
        result.either({}, { local.writeContactList(it) })
        return network.readUserList(request)
    }

    override fun readUser(request: ReadUserInteractor.Request): Either<Failure, UserEntity> {
        val result = local.readUser(request)
        return when {
            result.isLeft -> readFromNetwork(request)
            else -> result
        }

    }

    private fun readFromNetwork(request: ReadUserInteractor.Request): Either<Failure, UserEntity> {
        val result = network.readUser(request)
        result.either({}, { local.writeContactList(listOf(it)) })
        return result
    }

}