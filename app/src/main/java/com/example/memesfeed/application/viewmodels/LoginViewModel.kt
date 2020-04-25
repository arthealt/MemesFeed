package com.example.memesfeed.application.viewmodels

import androidx.lifecycle.*
import com.example.memesfeed.application.intent.StateLogin
import com.example.memesfeed.data.remote.models.LoginUserRequestDto
import com.example.memesfeed.domain.LoginRepository
import kotlinx.coroutines.*

class LoginViewModel (private val repository: LoginRepository): ViewModel(), LifecycleObserver {

    val state: MutableLiveData<StateLogin> = MutableLiveData<StateLogin>().apply { value = StateLogin.NormalState }

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    fun auth(user: LoginUserRequestDto) {
        state.apply { value = StateLogin.LoadingState }
        viewModelScope.launch {
            try {
                repository.auth(user)

                launch(Dispatchers.Main) {
                    state.apply { value = StateLogin.SuccessLogin }
                }
            } catch (error: Exception) {
                launch(Dispatchers.Main) {
                    state.apply { value = StateLogin.ErrorLogin(error.message) }
                }
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        viewModelJob.cancelChildren()
    }

}