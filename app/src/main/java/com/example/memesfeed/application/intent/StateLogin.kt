package com.example.memesfeed.application.intent

sealed class StateLogin {
    object NormalState: StateLogin()
    object LoadingState: StateLogin()
    object SuccessLogin: StateLogin()
    class ErrorLogin(val error: String?): StateLogin()
}