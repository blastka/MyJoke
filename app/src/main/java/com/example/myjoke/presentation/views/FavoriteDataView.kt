package com.example.myjoke.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import com.example.myjoke.R
import com.example.myjoke.presentation.State

class FavoriteDataView: LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    private val checkBox: CheckBox
    private val textView: CorrectTextView
    private val imageButton: CorrectImageButton
    private val actionButton: CorrectButton
    private val progress: CorrectProgress

    init {
        orientation = VERTICAL
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.favorite_data_view, this, true)
        checkBox = getChildAt(0) as CheckBox
        val linear = getChildAt(1) as LinearLayout
        textView = linear.findViewById(R.id.jokeText)
        imageButton = linear.findViewById(R.id.imageButton)
        progress = getChildAt(2) as CorrectProgress
        actionButton = getChildAt(3) as CorrectButton
    }

    fun listenChanges(block: (cached: Boolean)-> Unit){
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            block.invoke(isChecked)
        }
    }

    fun handleChangeButton(block:() -> Unit){
        imageButton.setOnClickListener {
            block.invoke()
        }
    }

    fun handleActionButton(block:() -> Unit) {
        actionButton.setOnClickListener {
            block.invoke()
        }
    }

    fun show(state: State){
        state.show(progress, actionButton, textView, imageButton)
    }

}