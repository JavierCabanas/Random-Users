package me.cabanas.javi.randomusers.features.users.repository.network

import android.util.Log
import me.cabanas.javi.randomusers.core.API_SEED
import me.cabanas.javi.randomusers.core.BASE_URL
import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserInteractor
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.features.users.domain.model.UserEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUsersClient : UserNetworkDataSource {
    val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiClient = retrofit.create(RandomUsersService::class.java)

    override fun readUserList(request: ReadUserListInteractor.Request):
            Either<Failure, List<UserEntity>> {
        return try {
            val call = apiClient.readUserList(request.size, request.page + 1, API_SEED)
            val result = call.execute()
            Log.d("API CALL",result.message())
            when {
                result.isSuccessful -> Either.Right(result.body()?.results ?: emptyList())
                else -> Either.Left(Failure.RemoteRepoFailure(result.message()))
            }
        } catch (e: Exception) {
            Either.Left(Failure.RemoteRepoFailure(e.message ?: ""))
        }
    }

    override fun readUser(request: ReadUserInteractor.Request): Either<Failure, UserEntity> {
        return try {
            val call = apiClient.readUser(request.id, API_SEED)
            val result = call.execute()
            when {
                result.isSuccessful && result.body()?.results?.get(0) != null ->
                    Either.Right(result.body()!!.results[0])

                else -> Either.Left(Failure.RemoteRepoFailure(result.message()))
            }
        } catch (e: Exception) {
            Either.Left(Failure.RemoteRepoFailure(e.message ?: ""))
        }
    }
}
