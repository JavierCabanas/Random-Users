package me.cabanas.javi.randomusers.feature.users.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import me.cabanas.javi.randomusers.feature.users.domain.model.*

class UsersViewModel : ViewModel() {

    var userList = mutableListOf<UserEntity>()
    var users: MutableLiveData<List<UserEntity>> = MutableLiveData()

    private var page = 0

    fun loadUsers() {
        userList.addAll(listOf<UserEntity>(UserEntity("male", NameEntity("javi", "canamas"),
                LocationEntity("", "", "", ""), "jhasjkd@askldj.com",
                "12347", "123456", PictureEntity("", "", ""),
                RegisteredEntity("", 0))))
        users.value = userList.distinctBy { it.email }
    }

}