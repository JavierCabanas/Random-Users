package me.cabanas.javi.randomusers.features.users.repository.network

import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.features.users.domain.model.UserEntity

interface UserNetworkDataSource {
    fun readUserList(request: ReadUserListInteractor.UserListRequest):
            Either<Failure, List<UserEntity>>
}
