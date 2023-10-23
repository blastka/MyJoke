package com.example.myjoke.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myjoke.App
import com.example.myjoke.R
import com.example.myjoke.presentation.views.FavoriteDataView

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: CommonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as App).viewModel
        val favoriteDataView = findViewById<FavoriteDataView>(R.id.favouriteView)
        favoriteDataView.linkWith(viewModel)
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


