package com.example.memesfeed.application.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.memesfeed.application.utils.NetworkUtil
import com.example.memesfeed.data.storage.UserStorage
import com.example.memesfeed.domain.implementations.LoginRepositoryImpl
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(
    private val networkUtil: NetworkUtil,
    private val repository: LoginRepositoryImpl,
    private val userStorage: UserStorage
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return LoginViewModel(networkUtil, repository, userStorage) as T
    }
}