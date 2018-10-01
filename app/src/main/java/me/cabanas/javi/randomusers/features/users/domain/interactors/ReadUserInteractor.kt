package me.cabanas.javi.randomusers.features.users.domain.interactors

import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.core.interactor.BaseInteractor
import me.cabanas.javi.randomusers.features.users.domain.model.UserEntity
import me.cabanas.javi.randomusers.features.users.repository.UserRepository
import kotlin.coroutines.experimental.CoroutineContext

class ReadUserInteractor(private val repository: UserRepository,
                         coroutineContext: CoroutineContext) :
        BaseInteractor<ReadUserInteractor.Request, UserEntity>(coroutineContext) {

    override suspend fun run(request: Request): Either<Failure, UserEntity> =
            repository.readUser(request)


    data class Request(val id: String)

}
