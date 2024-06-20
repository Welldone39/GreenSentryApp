package com.wildan.greensentry.customview

import android.content.Context
import android.graphics.Rect
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.wildan.greensentry.R

class PasswordInput :AppCompatEditText, View.OnTouchListener {
    var passwordValid: Boolean = false

    init {
        gitInit ()
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

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Do Nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validPassword()
            }

            override fun afterTextChanged(p0: Editable?) {
                //Do Nothing
            }

        })
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return false
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (!focused) {
            validPassword()
        }
    }

    private fun validPassword() {
        passwordValid = (text?.length ?: 0) >= 8
        error = if (!passwordValid) {
            resources.getString(R.string.character_password)
        } else {
            null
        }
    }
}