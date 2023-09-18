package com.example.myjoke.presentation

import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.myjoke.JokeApp
import com.example.myjoke.R
import com.example.myjoke.presentation.views.CorrectButton
import com.example.myjoke.presentation.views.CorrectImageButton
import com.example.myjoke.presentation.views.CorrectProgress
import com.example.myjoke.presentation.views.CorrectTextView

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: JokeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel
        val buttonJoke = findViewById<CorrectButton>(R.id.jokeButton)
        val jokeText = findViewById<CorrectTextView>(R.id.jokeText)
        val progress = findViewById<CorrectProgress>(R.id.progressBar)
        val imageButton = findViewById<CorrectImageButton>(R.id.imageButton)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)

        imageButton.setOnClickListener {
            viewModel.changeStateFavorites()
        }
        viewModel.observe(this) { state ->
            state.show(progress, buttonJoke, jokeText, imageButton)
        }

        buttonJoke.setOnClickListener {
            viewModel.joke()
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.changeCachedStatus(isChecked)
        }
    }
}


