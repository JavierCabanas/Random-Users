package me.cabanas.javi.randomusers.features.users.repository.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUsersService {
    @GET(".")
    fun readUserList(@Query("results") pageSize: Int = 10,
                     @Query("page") pageNumber: Int = 1,
                     @Query("seed") seed: String): Call<RandomContactsResponse>

    @GET(".")
    fun readUser(@Query("uuid") id: String,
                 @Query("seed") seed: String): Call<RandomContactsResponse>
}