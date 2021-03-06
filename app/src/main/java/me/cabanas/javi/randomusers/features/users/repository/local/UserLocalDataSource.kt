package me.cabanas.javi.randomusers.features.users.repository.local

import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserInteractor
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.features.users.domain.model.UserEntity

interface UserLocalDataSource {
    fun readUserList(request: ReadUserListInteractor.Request):
            Either<Failure, List<UserEntity>>

    fun writeContactList(users: List<UserEntity>)

    fun readUser(request: ReadUserInteractor.Request): Either<Failure, UserEntity>
}
