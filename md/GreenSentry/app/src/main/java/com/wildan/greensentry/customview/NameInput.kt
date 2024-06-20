package com.wildan.greensentry.customview

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.wildan.greensentry.R

class NameInput : AppCompatEditText, View.OnFocusChangeListener {

    var validName = false

    constructor(p0: Context) : super(p0) {
        gitInit()
    }


    constructor(p0: Context, p2: AttributeSet?) : super(p0, p2) {
        gitInit()
    }

    constructor(p0: Context, p1: AttributeSet?, p2: Int) : super(p0, p1, p2) {
        gitInit()
    }

    private fun gitInit() {

        background = ContextCompat.getDrawable(context, R.drawable.board)

        inputType = InputType.TYPE_CLASS_TEXT

        onFocusChangeListener = this
    }



    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) {
            validName()
        }
    }

    private fun validName() {
        validName = text.toString().trim().isNotEmpty()
        error = if (!validName) {
            resources.getString(R.string.nameEmpty)
        } else {
            null
        }
    }
}