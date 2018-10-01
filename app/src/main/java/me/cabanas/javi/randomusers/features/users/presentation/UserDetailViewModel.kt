package me.cabanas.javi.randomusers.features.users.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import me.cabanas.javi.randomusers.core.error.Failure
import me.cabanas.javi.randomusers.features.users.domain.interactors.ReadUserInteractor
import me.cabanas.javi.randomusers.features.users.domain.model.UserEntity

class UserDetailViewModel(private val readUser: ReadUserInteractor) : ViewModel() {

    var user = MutableLiveData<UserEntity>()
    val failure = MutableLiveData<Failure>()

    fun loadUser(id: String) {
        readUser(ReadUserInteractor.Request(id)) {
            it.either(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    private fun handleSuccess(userEntity: UserEntity) {
        user.value = userEntity
    }

}
