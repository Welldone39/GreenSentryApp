package com.wildan.greensentry.view

import LoginViewModel
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.wildan.greensentry.MainActivity
import com.wildan.greensentry.R
import com.wildan.greensentry.SessionManager
import com.wildan.greensentry.databinding.ActivityLoginBinding
import com.wildan.greensentry.model.BaseResponse
import com.wildan.greensentry.model.LoginResponse

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val token = SessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }

        viewModel.loginResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }

                else -> {
                    stopLoading()
                }
            }
        }

        viewModel.ValidationResult.observe(this) { validationState ->
            when (validationState) {
                LoginViewModel.EmailValidationState.WRONG_PASSWORD -> {
                    Toast.makeText(this,"Wrong password or email", Toast.LENGTH_SHORT).show()
                    stopLoading()
                }
                else -> {
                    // Do nothing for valid email
                }
            }
        }

        // Menangani klik pada tombol Register
        val btnRegister = findViewById<TextView>(R.id.button_register)
        btnRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            doLogin()
        }
        val showPasswordCheckBox = findViewById<CheckBox>(R.id.open_password_login)
        val passwordInput = binding.edLoginPassword

        showPasswordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Show password
                passwordInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                // Hide password
                passwordInput.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

    }

    private fun navigateToHome() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()  // Pastikan LoginActivity tidak dapat kembali
    }

    private fun doLogin() {
        val email = binding.edLoginEmail.text.toString()
        val pwd = binding.edLoginPassword.text.toString()
        viewModel.loginUser(email = email, pwd = pwd)
    }

    private fun showLoading() {
        binding.progressbarLogin.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressbarLogin.visibility = View.GONE
    }

    private fun processLogin(data: LoginResponse?) {
        showToast("Success: " + data?.message)
        if (!data?.token.isNullOrEmpty()) {
            data?.token?.let {
                SessionManager.saveAuthToken(this, it)
                navigateToHome()
            }
        } else {
            Log.e("LoginActivity", "Token is null or empty")
        }
    }

    private fun processError(msg: String?) {
        showToast("Login Error: " + msg)
        Log.e("LoginActivity", "Login error: $msg")
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
