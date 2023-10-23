package com.example.myjoke.domain

import com.example.myjoke.presentation.UiState

interface ItemDomain {

    fun toUi(): UiState

    data class Base(private val setup: String, private val punchline: String, private val favourite: Boolean) : ItemDomain {
        override fun toUi(): UiState {
            return if (favourite)
                UiState.FavoriteState(setup, punchline)
            else
                UiState.BaseState(setup, punchline)
        }
    }

    data class Fail(private val error: String) : ItemDomain {
        override fun toUi(): UiState {
            return UiState.FailedState(error)
        }
    }
}