package me.cabanas.javi.randomusers.feature.users.repository

import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.feature.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.feature.users.domain.model.UserEntity
import me.cabanas.javi.randomusers.feature.users.repository.network.UserNetworkDatasource

class UserRepositoryImpl(val network: UserNetworkDatasource) : UserRepository {
    override fun readContactList(request: ReadUserListInteractor.UserListRequest):
            Either<Failure, List<UserEntity>> = network.readContactList(request)
}