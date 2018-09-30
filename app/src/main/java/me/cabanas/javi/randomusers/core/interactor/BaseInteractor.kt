package me.cabanas.javi.randomusers.core.interactor

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import me.cabanas.javi.randomusers.core.functional.Either
import kotlin.coroutines.experimental.CoroutineContext

abstract class BaseInteractor<in Request, out Response>(val coroutineContext: CoroutineContext)
        where Response : Any {

    suspend abstract fun run(request: Request): Either<Error, Response>

    operator fun invoke(request: Request,onResult: (Either<Error, Response>) -> Unit) {
        val job = async(CommonPool) { run(request) }
        launch(coroutineContext) { onResult.invoke(job.await()) }
    }

}