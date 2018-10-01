package me.cabanas.javi.randomusers.features.users.domain.model

import com.google.gson.annotations.SerializedName

data class UserEntity(@SerializedName("gender") val gender: String,
                      @SerializedName("name") val name: NameEntity,
                      @SerializedName("location") val location: LocationEntity,
                      @SerializedName("id") val email: String,
                      @SerializedName("phone") val phone: String,
                      @SerializedName("cell") val cell: String,
                      @SerializedName("picture") val picture: PictureEntity,
                      @SerializedName("registered") val registered: RegisteredEntity,
                      @SerializedName("login") val login: LoginEntity)

data class NameEntity(@SerializedName("first") val first: String,
                      @SerializedName("last") val last: String)

data class LocationEntity(@SerializedName("street") val street: String,
                          @SerializedName("city") val city: String,
                          @SerializedName("state") val state: String,
                          @SerializedName("postcode") val postcode: String)

data class PictureEntity(@SerializedName("large") val large: String,
                         @SerializedName("medium") val medium: String,
                         @SerializedName("thumbnail") val thumbnail: String)

data class RegisteredEntity(@SerializedName("date") val date: String,
                            @SerializedName("age") val age: Int)

data class LoginEntity(@SerializedName("uuid") val uuid: String,
                       @SerializedName("username") val userName: String)