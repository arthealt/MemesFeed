package com.example.memesfeed.application.utils

import android.content.Context
import com.example.memesfeed.R
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject

class ValidatorUtil @Inject constructor(private val context: Context) {

    fun isLoginValid(login: String, loginInput: TextInputLayout): Boolean {
        return if (login.isNotEmpty()) {
            loginInput.error = null
            true
        } else {
            loginInput.error = context.getString(R.string.empty_field)
            loginInput.errorIconDrawable = null
            false
        }
    }

    fun isPasswordValid(password: String, passwordInput: TextInputLayout): Boolean {
        return if (password.length > 5) {
            passwordInput.error = null
            passwordInput.helperText = null
            true
        } else {
            if (password.isEmpty()) {
                passwordInput.error = context.getString(R.string.empty_field)
                passwordInput.errorIconDrawable = null
            } else {
                passwordInput.helperText = context.getString(R.string.password_short)
            }
            false
        }
    }

}