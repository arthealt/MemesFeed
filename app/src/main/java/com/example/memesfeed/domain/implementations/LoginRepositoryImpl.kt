package com.example.memesfeed.domain.implementations

import android.content.SharedPreferences
import com.example.memesfeed.application.helpers.UserInfoDB
import com.example.memesfeed.data.remote.ApiService
import com.example.memesfeed.data.remote.models.AuthInfoDto
import com.example.memesfeed.data.remote.models.LoginUserRequestDto
import com.example.memesfeed.domain.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val apiService: ApiService, private val pref: SharedPreferences): LoginRepository {

    override suspend fun auth(user: LoginUserRequestDto): AuthInfoDto {
        val authInfo = withContext(Dispatchers.Default) {
            apiService.auth(user)
        }

        val edit = pref.edit()
        edit.putString(UserInfoDB.TOKEN, authInfo.accessToken)
        edit.putInt(UserInfoDB.ID, authInfo.userInfo.id)
        edit.putString(UserInfoDB.USERNAME, authInfo.userInfo.username)
        edit.putString(UserInfoDB.FIRSTNAME, authInfo.userInfo.firstName)
        edit.putString(UserInfoDB.LASTNAME, authInfo.userInfo.lastName)
        edit.putString(UserInfoDB.DESCRIPTION, authInfo.userInfo.userDescription)
        edit.apply()

        return authInfo
    }
}