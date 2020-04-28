package com.example.memesfeed.data.storage

import android.content.SharedPreferences
import com.example.memesfeed.data.remote.models.AuthInfoDto
import javax.inject.Inject

class UserStorage @Inject constructor(private val pref: SharedPreferences) {

    fun saveUser(authInfo: AuthInfoDto) {
        val edit = pref.edit()
        edit.putString(UserInfoDB.TOKEN, authInfo.accessToken)
        edit.putInt(UserInfoDB.ID, authInfo.userInfo?.id ?: 0)
        edit.putString(UserInfoDB.USERNAME, authInfo.userInfo?.username)
        edit.putString(UserInfoDB.FIRSTNAME, authInfo.userInfo?.firstName)
        edit.putString(UserInfoDB.LASTNAME, authInfo.userInfo?.lastName)
        edit.putString(UserInfoDB.DESCRIPTION, authInfo.userInfo?.userDescription)
        edit.apply()
    }

}