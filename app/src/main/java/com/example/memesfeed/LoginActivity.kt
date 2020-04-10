package com.example.memesfeed

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.LoginTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // hide actionBar
        supportActionBar?.hide()
    }

    private fun snackError(error: String) {
        val snackbar = Snackbar.make(snack_bar, error, Snackbar.LENGTH_LONG)
        snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.colorError))
        snackbar.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackbar.show()
    }
}
