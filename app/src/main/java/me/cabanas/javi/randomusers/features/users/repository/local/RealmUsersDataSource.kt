package me.cabanas.javi.randomusers.features.users.repository.local

import android.util.Log
import io.realm.Realm
import io.realm.kotlin.where
import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserInteractor
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.features.users.domain.model.*

class RealmUsersDataSource : UserLocalDataSource {
    private val TAG = UserLocalDataSource::class.java.simpleName

    override fun readUserList(request: ReadUserListInteractor.Request): Either<Failure, List<UserEntity>> {
        val start = request.size * request.page
        val end = start + request.size
        val db = Realm.getDefaultInstance()
        val results = db.where<LocalUser>().findAll()
        val pagedResults = if (results.lastIndex >= end) results.subList(start, end) else emptyList<LocalUser>()
        return when {
            !pagedResults.isEmpty() -> {
                val userList = pagedResults.map { toDomainEntity(it) }
                db.close()
                Either.Right(userList)
            }
            else -> {
                db.close()
                Either.Left(Failure.LocalRepoFailure("empty db"))
            }
        }
    }

    private fun toDomainEntity(localUser: LocalUser): UserEntity = with(localUser) {
        val login = LoginEntity(uuid, userName)
        val name = NameEntity(firstName, lastName)
        val location = LocationEntity(street, city, state, postCode)
        val picture = PictureEntity(largePicture, mediumPicture, thumbnail)
        val registered = RegisteredEntity(registeredDate, registeredAge)
        UserEntity(gender, name, location, email, phone, cell, picture, registered, login)
    }

    override fun writeContactList(users: List<UserEntity>) {
        val db = Realm.getDefaultInstance()
        try {
            db.executeTransaction { realm ->
                val results = realm.copyToRealmOrUpdate(users.map { toLocalEntity(it) })
                Log.d(TAG, "write " + results.size + " items to local storage")
            }
        } catch (exception: Exception) {
            Log.d(TAG, "Error writing on local storage")
        } finally {
            db.close()
        }
    }


    private fun toLocalEntity(user: UserEntity) = LocalUser().apply {
        uuid = user.login.uuid
        userName = user.login.userName
        gender = user.gender
        firstName = user.name.first
        lastName = user.name.last
        street = user.location.street
        state = user.location.state
        city = user.location.city
        postCode = user.location.postcode
        email = user.email
        phone = user.phone
        largePicture = user.picture.large
        mediumPicture = user.picture.medium
        thumbnail = user.picture.thumbnail
        registeredAge = user.registered.age
        registeredDate = user.registered.date
    }

    override fun readUser(request: ReadUserInteractor.Request): Either<Failure, UserEntity> {
        val db = Realm.getDefaultInstance()
        val result = db.where<LocalUser>().equalTo("uuid", request.id).findFirst()

        return when (result) {
            null -> {
                db.close()
                Either.Left(Failure.LocalRepoFailure("not found"))
            }
            else -> {
                val user = toDomainEntity(result)
                db.close()
                Either.Right(user)
            }
        }
    }
}
