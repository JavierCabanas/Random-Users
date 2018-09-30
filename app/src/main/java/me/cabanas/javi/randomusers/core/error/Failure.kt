package me.cabanas.javi.randomusers.core.error

sealed class Failure(val reason: String) {
    class RemoteRepoFailure(reason: String) : Failure(reason)
    class LocalRepoFailure(reason: String) : Failure(reason)
    class InteractorFailure(reason: String) : Failure(reason)
}