package me.cabanas.javi.randomusers.features.users.domain.interactors

import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.core.interactor.BaseInteractor
import me.cabanas.javi.randomusers.features.users.domain.model.UserEntity
import me.cabanas.javi.randomusers.features.users.repository.UserRepository
import kotlin.coroutines.experimental.CoroutineContext

class ReadUserListInteractor(private val repository: UserRepository,
                             coroutineContext: CoroutineContext) :
        BaseInteractor<ReadUserListInteractor.Request, List<UserEntity>>(coroutineContext) {

    override suspend fun run(request: Request): Either<Failure, List<UserEntity>> =
            repository.readUserList(request)


    data class Request(val page: Int, val size: Int)
}