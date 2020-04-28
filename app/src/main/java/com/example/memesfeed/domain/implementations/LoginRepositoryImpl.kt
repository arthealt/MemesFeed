package com.example.memesfeed.domain.implementations

import com.example.memesfeed.data.remote.services.AuthService
import com.example.memesfeed.data.remote.models.AuthInfoDto
import com.example.memesfeed.data.remote.models.LoginUserRequestDto
import com.example.memesfeed.domain.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val authService: AuthService): LoginRepository {

    override suspend fun auth(user: LoginUserRequestDto): AuthInfoDto {
        return withContext(Dispatchers.Default) {
            authService.auth(user)
        }
    }
}