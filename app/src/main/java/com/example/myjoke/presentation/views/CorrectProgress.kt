package com.example.myjoke.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import com.example.myjoke.presentation.ShowView

class CorrectProgress : ProgressBar, ShowView {
    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs,
        defStyleAttr
    )

    //endregion
    override fun show(arg: Boolean) {
        isIndeterminate = arg
    }
}