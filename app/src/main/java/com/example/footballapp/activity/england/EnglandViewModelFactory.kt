package com.example.footballapp.activity.england

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.footballapp.repository.FootballRepository

class EnglandViewModelFactory(
    private val repository: FootballRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EnglandViewModel(repository) as T
    }
}