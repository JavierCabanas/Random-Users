package me.cabanas.javi.randomusers.feature.users.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.feature.users.domain.interactors.ReadUserListInteractor
import me.cabanas.javi.randomusers.feature.users.domain.model.*

class UsersViewModel(private val readUsers: ReadUserListInteractor) : ViewModel() {

    var userList = mutableListOf<UserEntity>()
    var users: MutableLiveData<List<UserEntity>> = MutableLiveData()

    private var page = 0

    fun loadUsers() = readUsers(ReadUserListInteractor.UserListRequest(page, 10)) {
        it.either(::handleFailure, ::handleSuccess)
    }

    private fun handleSuccess(result: List<UserEntity>) {
        userList.addAll(result)
        users.value = userList.distinctBy { it.email }
    }

    private fun handleFailure(failure: Failure) {

    }

}