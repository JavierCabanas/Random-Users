package me.cabanas.javi.randomusers.feature.users.repository

import me.cabanas.javi.randomusers.core.error.Error
import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.feature.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.feature.users.domain.model.UserEntity
import me.cabanas.javi.randomusers.feature.users.repository.network.UserNetworkDatasource

class UserRepositoriImpl(val network: UserNetworkDatasource) : UserRepository {
    override fun readContactList(request: ReadUserListInteractor.UserListRequest):
            Either<Error, List<UserEntity>> = network.readContactList(request)
}