package me.cabanas.javi.randomusers.features.users.repository.local

import android.util.Log
import io.realm.Realm
import io.realm.kotlin.where
import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.core.functional.Either
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.features.users.domain.model.*
import java.lang.Exception

class RealmUsersDataSource : UserLocalDataSource {

    private val TAG = UserLocalDataSource::class.java.simpleName

    override fun readUserList(request: ReadUserListInteractor.UserListRequest): Either<Failure, List<UserEntity>> {
        val start = request.size * request.page
        val end = start + request.size
        val db = Realm.getDefaultInstance()
        val results = db.where<LocalUser>().findAll()
        val pagedResults = if (results.lastIndex >= end) results.subList(start, end) else emptyList<LocalUser>()
        return if (!pagedResults.isEmpty()) {
            val userList = pagedResults.map { toDomainEntity(it) }
            db.close()
            Either.Right(userList)
        } else {
            db.close()
            Either.Left(Failure.LocalRepoFailure("empty db"))
        }
    }

    private fun toDomainEntity(localUser: LocalUser) = localUser.let {
        val name = NameEntity(it.firstName, it.lastName)
        val location = LocationEntity(it.street, it.city, it.state, it.postCode)
        val picture = PictureEntity(it.largePicture, it.mediumPicture, it.thumbnail)
        val registered = RegisteredEntity(it.registeredDate, it.registeredAge)
        UserEntity(it.gender, name, location, it.email, it.phone, it.cell, picture, registered)
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

    private fun toLocalEntity(user: UserEntity) =
            LocalUser().apply {
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

}