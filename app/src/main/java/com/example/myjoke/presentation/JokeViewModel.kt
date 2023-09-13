package com.example.myjoke.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjoke.domain.JokeInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeViewModel(
    private val interactor: JokeInteractor
) : ViewModel(), JokeFetcher, FavoriteChooser, JokeStatusChanger {

    val liveData = MutableLiveData<Pair<String, Int>>()

    override fun joke() {
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(interactor.joke().toUi().getData())
        }
    }

    override fun changeStateFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(interactor.changeStateFavorites().toUi().getData())
        }
    }

    override fun changeJokeStatus(cached: Boolean) {
        interactor.changeJokeStatus(cached)
    }

}

interface JokeFetcher {
    fun joke()
}

interface JokeStatusChanger {
    fun changeJokeStatus(cached: Boolean)
}

interface FavoriteChooser {
    fun changeStateFavorites()
}

interface Init {
    fun init()
}
