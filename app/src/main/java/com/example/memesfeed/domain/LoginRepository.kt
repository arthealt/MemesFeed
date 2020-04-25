package com.example.memesfeed.domain

import com.example.memesfeed.data.remote.models.AuthInfoDto
import com.example.memesfeed.data.remote.models.LoginUserRequestDto

interface LoginRepository {

    suspend fun auth(user: LoginUserRequestDto): AuthInfoDto

}