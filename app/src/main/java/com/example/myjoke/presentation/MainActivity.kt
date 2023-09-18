package com.example.myjoke.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myjoke.JokeApp
import com.example.myjoke.R

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: JokeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel
        val buttonJoke = findViewById<Button>(R.id.jokeButton)
        val jokeText = findViewById<TextView>(R.id.jokeText)
        val progress = findViewById<ProgressBar>(R.id.progressBar)
        val imageButton = findViewById<ImageButton>(R.id.imageButton)
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


