package me.cabanas.javi.randomusers.features.users.repository.network

import me.cabanas.javi.randomusers.core.API_SEED
import me.cabanas.javi.randomusers.core.BASE_URL
import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.features.users.domain.model.UserEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroftiUsersClient : UserNetworkDataSource {

    val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiClient = retrofit.create(RandomUsersService::class.java)

    override fun readUserList(request: ReadUserListInteractor.UserListRequest):
            Either<Failure, List<UserEntity>> {
        val call = apiClient.readContactList(request.size, request.page + 1, API_SEED)
        try {
            val result = call.execute()
            return if (result.isSuccessful) {
                Either.Right(result.body()?.results ?: emptyList())
            } else {
                Either.Left(Failure.RemoteRepoFailure(result.message()))
            }
        } catch (e: Exception) {
            return Either.Left(Failure.RemoteRepoFailure(e.message!!))
        }

    }
}