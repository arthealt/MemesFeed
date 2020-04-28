package com.example.memesfeed.application.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.memesfeed.R
import com.example.memesfeed.application.utils.NetworkUtil
import com.example.memesfeed.application.intent.ScreenState
import com.example.memesfeed.application.utils.ValidatorUtil
import com.example.memesfeed.application.viewmodels.LoginViewModel
import com.example.memesfeed.application.viewmodels.LoginViewModelFactory
import com.example.memesfeed.di.App
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), View.OnClickListener {

    @Inject
    lateinit var validatorUtil: ValidatorUtil
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
        initObservers()
    }

    private fun initObservers() {
        viewModel.state.observe(this, Observer { value ->
            when (value) {

                is ScreenState.LoadingState -> {
                    hideKeyboard()
                    progress_bar.visibility = View.VISIBLE
                    btn_auth.text = ""
                    btn_auth.isEnabled = false
                }

                is ScreenState.SuccessState -> {
                    toMain()
                }

                is ScreenState.ErrorState -> {
                    progress_bar.visibility = View.GONE
                    btn_auth.text = getString(R.string.auth)
                    btn_auth.isEnabled = true
                    snackError(value.error ?: getString(R.string.error_unexpected))
                }

            }
        })
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_auth) {
            val login = login_edit_text.text.toString().trim()
            val password = password_edit_text.text.toString()

            if (validatorUtil.isLoginValid(login, login_input) &&
                validatorUtil.isPasswordValid(password, password_input)) {
                viewModel.auth(login, password)
            }
        }
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

}