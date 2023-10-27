package com.example.myjoke.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myjoke.App
import com.example.myjoke.R
import com.example.myjoke.presentation.views.FavoriteDataView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val jokeViewModel = (application as App).jokeViewModel
        val favoriteDataView = findViewById<FavoriteDataView>(R.id.jokeFavouriteView)
        favoriteDataView.linkWith(jokeViewModel)

        jokeViewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }

        val quoteViewModel = (application as App).quoteViewModel
        val quoteDataView = findViewById<FavoriteDataView>(R.id.quoteFavouriteView)
        quoteDataView.linkWith(quoteViewModel)

        quoteViewModel.observe(this) { state ->
            quoteDataView.show(state)
        }

    }
}
