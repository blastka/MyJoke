package com.example.myjoke.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myjoke.JokeApp
import com.example.myjoke.R
import com.example.myjoke.data.JokeUi

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

        val callback =
            object : TextCallback {
                override fun setText(text: String) {
                    progress.isIndeterminate = false
                    jokeText.text = text
                    buttonJoke.isEnabled = true
                }

                override fun setIcon(id: JokeUi) {
                    image.setImageResource(id.getIconId())
                }
            }

        buttonJoke.setOnClickListener {
            progress.isIndeterminate = true
            viewModel.joke(callback)
            buttonJoke.isEnabled = false
        }
    }
}

interface TextCallback {
    fun setText(text: String)
    fun setIcon(id: JokeUi)
}