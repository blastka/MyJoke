package com.example.myjoke.presentation

import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myjoke.R
import com.example.myjoke.core.ResourceManager
import com.example.myjoke.data.JokeCloudDataSource
import com.example.myjoke.data.RetrofitBuilder

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonJoke = findViewById<Button>(R.id.jokeButton)
        val jokeText = findViewById<TextView>(R.id.jokeText)
        val progress = findViewById<ProgressBar>(R.id.progressBar)

        val jokeCloudDataSource = JokeCloudDataSource.BaseEnqueue(
            RetrofitBuilder().retrofit,
            object : TextCallback {
                override fun setTextSuccess(text: String) {
                    progress.isIndeterminate = false
                    jokeText.text = text
                    buttonJoke.isEnabled = true
                }

                override fun setTextError(text: String) {
                    progress.isIndeterminate = false
                    jokeText.text = text
                    buttonJoke.isEnabled = true
                }
            },
            ResourceManager.Base(applicationContext)
        )

        buttonJoke.setOnClickListener {
            progress.isIndeterminate = true
            jokeCloudDataSource.getRandomJoke()
            buttonJoke.isEnabled = false
        }
    }
}

interface TextCallback {
    fun setTextSuccess(text: String)
    fun setTextError(text: String)
}