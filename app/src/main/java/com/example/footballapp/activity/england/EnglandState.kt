package com.example.footballapp.activity.england

import com.example.footballapp.model.England

sealed class EnglandState {
    object Loading: EnglandState()
    data class Error(val error: String): EnglandState()
    data class Success(val list: List<England>): EnglandState()
}