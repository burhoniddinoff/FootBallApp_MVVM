package com.example.footballapp.activity.france

import com.example.footballapp.model.Club
import com.example.footballapp.model.France

sealed class FranceState {
    object Loading: FranceState()
    data class Error(val error: String): FranceState()
    data class Success(val list: List<France>): FranceState()
}