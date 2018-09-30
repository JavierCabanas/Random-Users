package me.cabanas.javi.randomusers.feature.users.repository.network

import me.cabanas.javi.randomusers.core.error.Error
import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.feature.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.feature.users.domain.model.UserEntity

interface UserNetworkDatasource {
    fun readContactList(request: ReadUserListInteractor.UserListRequest):
            Either<Error, List<UserEntity>>
}
