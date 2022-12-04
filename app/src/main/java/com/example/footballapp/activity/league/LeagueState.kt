package com.example.footballapp.activity.league

import com.example.footballapp.model.Club

sealed class LeagueState {
    object Loading: LeagueState()
    data class Error(val error: String): LeagueState()
    data class Success(val list: List<Club>): LeagueState()
}