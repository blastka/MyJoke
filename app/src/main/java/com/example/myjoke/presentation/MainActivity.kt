package com.example.myjoke.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myjoke.App
import com.example.myjoke.R
import com.example.myjoke.presentation.views.FavoriteDataView

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: JokeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as App).viewModel
        val favoriteDataView = findViewById<FavoriteDataView>(R.id.favouriteView)

        favoriteDataView.handleChangeButton {
            viewModel.changeStateFavorites()
        }

        viewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }

        favoriteDataView.handleActionButton {
            viewModel.joke()
        }

        favoriteDataView.listenChanges { isChecked->
            viewModel.changeCachedStatus(isChecked)
        }
    }


}


