package me.cabanas.javi.randomusers.core.error

sealed class Error(val reason: String) {
    class NoInternetConnectionError(reason: String) : Error(reason)
    class RemoteRepoError(reason: String) : Error(reason)
    class LocalRepoError(reason: String) : Error(reason)
    class InteractorError(reason: String) : Error(reason)
}