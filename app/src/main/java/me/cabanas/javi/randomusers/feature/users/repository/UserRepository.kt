package me.cabanas.javi.randomusers.feature.users.repository

import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.feature.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.feature.users.domain.model.UserEntity

interface UserRepository {
    fun readContactList(request: ReadUserListInteractor.UserListRequest):
            Either<Error, List<UserEntity>>
}
