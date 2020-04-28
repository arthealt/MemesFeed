package com.example.memesfeed.application.intent

sealed class ScreenState {
    object NormalState: ScreenState()
    object LoadingState: ScreenState()
    object SuccessState: ScreenState()
    class ErrorState(val error: String?): ScreenState()
}