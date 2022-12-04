package com.example.footballapp.activity.main

import com.example.footballapp.model.League

sealed class MainState  {
    object Loading: MainState()
    data class Error(val error: String): MainState()
    data class Success(val list: List<League>): MainState()
}