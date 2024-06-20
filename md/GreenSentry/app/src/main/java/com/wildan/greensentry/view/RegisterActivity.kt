package com.wildan.greensentry.view

import RegisterViewModel
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.wildan.greensentry.R
import com.wildan.greensentry.databinding.ActivityRegisterBinding
import com.wildan.greensentry.model.BaseResponse
import com.wildan.greensentry.model.RequestResponse


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.registerResult.observe(this) { result ->
            when (result) {
                is BaseResponse.Loading -> showLoading()
                is BaseResponse.Success -> {
                    stopLoading()
                    processRegistration(result.data)
                }
                is BaseResponse.Error -> processError(result.msg)
                else -> stopLoading()
            }
        }

        // Initialize views and set up listeners
        initView()

        binding.btnRegisterAccount.setOnClickListener {
            Log.d("RegisterActivity", "Register button clicked")
            doRegistration()
        }
    }

    private fun initView() {
        val showPasswordCheckBox = findViewById<CheckBox>(R.id.open_password_register)
        val passwordInput = binding.edRegisterPassword
        val passwordSameInput = binding.edRegisterPasswordsame

        showPasswordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Show password
                passwordInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
                passwordSameInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                // Hide password
                passwordInput.transformationMethod = PasswordTransformationMethod.getInstance()
                passwordSameInput.transformationMethod = PasswordTransformationMethod.getInstance()
            }

        }
    }

    private fun doRegistration() {
        val firstname = binding.edRegisterFirstname.text.toString()
        val lastname = binding.edRegisterLastname.text.toString()
        val email = binding.edRegisterEmail.text.toString()
        val pwd = binding.edRegisterPassword.text.toString()
        val pwdConfirm = binding.edRegisterPasswordsame.text.toString()

        if (pwd != pwdConfirm) {
            showToast("Password and confirmation password do not match")
            return
        }

        viewModel.registerUser(firstname, lastname, email, pwd)
    }

    private fun showLoading() {
        binding.progressbar2.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressbar2.visibility = View.GONE
    }

    private fun processRegistration(data: RequestResponse?) {
        showToast("Registration Success: " + data?.message)
        Log.d("RegisterActivity", "Registration successful: ${data?.message}")
        finish() // Finish this activity after successful registration
    }

    private fun processError(msg: String?) {
        if (msg == "Email already registered") {
            binding.edRegisterEmail.error = getString(R.string.email_already_used_message)
            Toast.makeText(this, "Email Sudah Digunakan", Toast.LENGTH_SHORT).show()
        } else {
            showToast("Registration Error: " + msg)
            Log.e("RegisterActivity", "Registration error: $msg")
        }
        stopLoading()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
