package me.cabanas.javi.randomusers.features.users.repository.local

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class LocalUser : RealmObject() {
    var gender: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var street: String = ""
    var city: String = ""
    var state: String = ""
    var postCode: String = ""
    @PrimaryKey
    var email: String = ""
    var phone: String = ""
    var cell: String = ""
    var largePicture: String = ""
    var mediumPicture: String = ""
    var thumbnail: String = ""
    var registeredDate: String = ""
    var registeredAge: Int = 0
}

