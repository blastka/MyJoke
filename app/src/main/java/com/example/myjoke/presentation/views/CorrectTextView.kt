package com.example.myjoke.presentation.views

import android.content.Context
import android.util.AttributeSet
import com.example.myjoke.presentation.ShowText

class CorrectTextView : androidx.appcompat.widget.AppCompatTextView,
    ShowText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(arg: String) {
        text = arg
    }
}