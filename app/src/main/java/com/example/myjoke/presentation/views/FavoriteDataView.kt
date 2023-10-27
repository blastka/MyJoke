package com.example.myjoke.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myjoke.R
import com.example.myjoke.presentation.BaseViewModel
import com.example.myjoke.presentation.State

class FavoriteDataView : LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private lateinit var checkBox: CheckBox
    private lateinit var textView: CorrectTextView
    private lateinit var imageButton: CorrectImageButton
    private lateinit var actionButton: CorrectButton
    private lateinit var progress: CorrectProgress


    fun init(attrs: AttributeSet) {
        orientation = VERTICAL
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.favorite_data_view,
            this,
            true
        )
        checkBox = getChildAt(0) as CheckBox
        val linear = getChildAt(1) as LinearLayout
        textView = linear.findViewById(R.id.jokeText)
        imageButton = linear.findViewById(R.id.imageButton)
        progress = getChildAt(2) as CorrectProgress
        actionButton = getChildAt(3) as CorrectButton

        val recyclerView = getChildAt(4) as RecyclerView


        context.theme.obtainStyledAttributes(attrs, R.styleable.FavoriteDataView, 0, 0).apply {
            try {
                val actionButtonText = getString(R.styleable.FavoriteDataView_actionButtonText)
                val checkBoxText = getString(R.styleable.FavoriteDataView_checkBoxText)
                checkBox.text = checkBoxText
                actionButton.text = actionButtonText
            } finally {
                recycle()
            }
        }

    }


    fun show(state: State) {
        state.show(progress, actionButton, textView, imageButton)
    }

    fun linkWith(viewModel: BaseViewModel){
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.changeCachedStatus(isChecked)
        }
        imageButton.setOnClickListener {
            viewModel.changeStateFavorites()
        }
        actionButton.setOnClickListener {
            viewModel.joke()
        }

    }
}
