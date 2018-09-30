package me.cabanas.javi.randomusers.features.users.repository.network

import com.google.gson.annotations.SerializedName
import me.cabanas.javi.randomusers.features.users.domain.model.UserEntity

data class RandomContactsResponse(@SerializedName("results")
                                  val results: List<UserEntity>,
                                  @SerializedName("info") val info: Info)

data class Info(@SerializedName("seed") val seed: String,
                @SerializedName("results") val results: Int,
                @SerializedName("page") val page: Int,
                @SerializedName("version") val version: String)
