package com.wildan.greensentry.customview

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.wildan.greensentry.R

class EmailInput : AppCompatEditText, View.OnFocusChangeListener {

    var emailValid = false
    private lateinit var sameEmail : String
    private var emailTaken = false


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

        inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

        onFocusChangeListener = this

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validEmail()
                if (emailTaken) {
                    emailTaken()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                //
            }

        })
    }

    private fun validEmail() {
        emailValid = Patterns.EMAIL_ADDRESS.matcher(text.toString().trim()).matches()
        error = if (!emailValid) {
            resources.getString(R.string.wrong_email)
        } else {
            null
        }
    }

    private fun emailTaken() {
        error = if (emailTaken && text.toString().trim() == sameEmail) {
            resources.getString(R.string.emailTake)
        } else {
            null
        }
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) {
            validEmail()
            if (emailTaken) {
                emailTaken()

            }
        }
    }

    fun messageError(msg: String, email: String) {
        sameEmail = email
        emailTaken = true
        error = if (text.toString().trim() == sameEmail) {
            msg
        } else {
            null
        }
    }
}