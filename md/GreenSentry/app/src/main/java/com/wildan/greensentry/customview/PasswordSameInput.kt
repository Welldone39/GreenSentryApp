package com.wildan.greensentry.customview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.wildan.greensentry.R

class PasswordSameInput : AppCompatEditText, View.OnFocusChangeListener {

    var passwordValid = false

    init {
        gitInit()
    }

    constructor(p0: Context) : super(p0) {
        gitInit()
    }

    constructor(p0: Context, p1: AttributeSet?) : super(p0, p1) {
        gitInit()
    }

    constructor(p0: Context, p1: AttributeSet?, p2: Int) : super(p0, p1, p2) {
        gitInit()
    }

    private fun gitInit() {
        background = ContextCompat.getDrawable(context, R.drawable.board)
        transformationMethod = PasswordTransformationMethod.getInstance()

        onFocusChangeListener = this

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validPassword()
            }

            override fun afterTextChanged(p0: Editable?) {
                //
            }

        })
    }

    private fun validPassword() {
        val password = text.toString().trim()
        val confirm =
            (parent as ViewGroup).findViewById<PasswordInput>(R.id.ed_register_password).text.toString()
                .trim()

        passwordValid = password.length >= 8 && password == confirm
        error = if (!passwordValid) {
            resources.getString(R.string.wrong_password)
        } else {
            null
        }
    }

    override fun onFocusChange(v: View?, p1: Boolean) {
        if (!hasFocus()) {
            validPassword()
        }
    }



}