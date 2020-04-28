package com.example.memesfeed.application.viewmodels

import androidx.lifecycle.*
import com.example.memesfeed.application.utils.NetworkUtil
import com.example.memesfeed.application.intent.ScreenState
import com.example.memesfeed.data.remote.models.LoginUserRequestDto
import com.example.memesfeed.data.storage.UserStorage
import com.example.memesfeed.domain.LoginRepository
import kotlinx.coroutines.*

class LoginViewModel (
    private val networkUtil: NetworkUtil,
    private val repository: LoginRepository,
    private val userStorage: UserStorage
): ViewModel(), LifecycleObserver {

    val state: MutableLiveData<ScreenState> =MutableLiveData<ScreenState>().apply {
        value = ScreenState.NormalState
    }

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    fun auth(login: String, password: String) {

        state.apply { value = ScreenState.LoadingState }

        if (networkUtil.isInternetConnection()) {
            val user = LoginUserRequestDto(login, password)

            viewModelScope.launch {
                try {
                    val authInfo = repository.auth(user)
                    userStorage.saveUser(authInfo)

                    launch(Dispatchers.Main) {
                        state.apply { value = ScreenState.SuccessState }
                    }
                } catch (error: Exception) {
                    launch(Dispatchers.Main) {
                        state.apply { value = ScreenState.ErrorState(error.message) }
                    }
                }
            }
        } else {
            state.apply { value = ScreenState.ErrorState(networkUtil.getErrorConnection()) }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        viewModelJob.cancelChildren()
    }

}