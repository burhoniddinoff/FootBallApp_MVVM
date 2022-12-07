package com.example.footballapp.activity.espain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.footballapp.repository.FootballRepository

class LeagueViewModelFactory(
    private val repository: FootballRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LeagueViewModel(repository) as T
    }
}