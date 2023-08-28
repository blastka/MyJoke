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
        val image = findViewById<ImageButton>(R.id.imageButton)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)

        val callback =
            object : ViewModelCallback {
                override fun setText(text: String) {
                    progress.isIndeterminate = false
                    jokeText.text = text
                    buttonJoke.isEnabled = true
                }

                override fun setIcon(id: Int) {
                    image.setImageResource(id)
                }
            }

        viewModel.init(callback)

        buttonJoke.setOnClickListener {
            progress.isIndeterminate = true
            viewModel.joke()
            buttonJoke.isEnabled = false
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.chooseFavorites(isChecked)
        }
    }
}

interface ViewModelCallback {
    fun setText(text: String)
    fun setIcon(id: Int)
}

