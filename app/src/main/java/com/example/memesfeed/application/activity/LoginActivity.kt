package com.example.memesfeed.application.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.memesfeed.R
import com.example.memesfeed.application.helpers.Util
import com.example.memesfeed.application.intent.StateLogin
import com.example.memesfeed.application.viewmodels.LoginViewModel
import com.example.memesfeed.application.viewmodels.LoginViewModelFactory
import com.example.memesfeed.data.remote.models.LoginUserRequestDto
import com.example.memesfeed.di.App
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var util: Util
    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.LoginTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        App.getComponent().inject(this)

        viewModel = ViewModelProviders.of(this, loginViewModelFactory)[LoginViewModel::class.java]
        lifecycle.addObserver(viewModel)

        btn_auth.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_auth) {
            val login = login_edit_text.text.toString().trim()
            val password = password_edit_text.text.toString()
            val user = LoginUserRequestDto(login, password)

            if (util.isInternetConnection()) {
                if (validateFields(login, password)) {
                    viewModel.auth(user)
                }
            } else {
                hideKeyboard()
                snackError(getString(R.string.error_connection))
            }

            viewModel.state.observe(this, Observer { value ->
                when (value) {

                    is StateLogin.LoadingState -> {
                        hideKeyboard()
                        progress_bar.visibility = View.VISIBLE
                        btn_auth.text = ""
                        btn_auth.isEnabled = false
                    }

                    is StateLogin.SuccessLogin -> {
                        toMain()
                    }

                    is StateLogin.ErrorLogin -> {
                        progress_bar.visibility = View.GONE
                        btn_auth.text = getString(R.string.auth)
                        btn_auth.isEnabled = true
                        snackError(value.error ?: getString(R.string.error_unexpected))
                    }

                }
            })
        }
    }

    private fun validateFields(login: String, password: String): Boolean {
        var valid = true

        if (login.isNotEmpty()) {
            login_input.error = null
        } else {
            login_input.error = getString(R.string.empty_field)
            login_input.errorIconDrawable = null
            valid = false
        }

        if (password.length > 5) {
            password_input.error = null
            password_input.helperText = null
        } else {
            if (password.isEmpty()) {
                password_input.error = getString(R.string.empty_field)
                password_input.errorIconDrawable = null
            } else {
                password_input.helperText = getString(R.string.password_short)
            }
            valid = false
        }

        return valid
    }

    private fun toMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun snackError(error: String) {
        val snackbar = Snackbar.make(snack_bar, error, Snackbar.LENGTH_LONG)
        snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.colorError))
        snackbar.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackbar.show()
    }

    private fun AppCompatActivity.hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        } else {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
    }
}