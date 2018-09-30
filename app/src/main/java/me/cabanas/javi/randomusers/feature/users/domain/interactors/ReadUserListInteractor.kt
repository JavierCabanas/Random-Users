package me.cabanas.javi.randomusers.feature.users.domain.interactors

import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.core.interactor.BaseInteractor
import me.cabanas.javi.randomusers.feature.users.domain.model.UserEntity
import me.cabanas.javi.randomusers.feature.users.repository.UserRepository
import kotlin.coroutines.experimental.CoroutineContext

class ReadUserListInteractor(private val repository: UserRepository,
                             coroutineContext: CoroutineContext) :
        BaseInteractor<ReadUserListInteractor.UserListRequest, List<UserEntity>>(coroutineContext) {

    override suspend fun run(request: UserListRequest): Either<Error, List<UserEntity>> {
        return repository.readContactList(request)
    }

    data class UserListRequest(val page: Int, val size: Int)
}