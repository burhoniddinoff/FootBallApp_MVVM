package com.example.footballapp.activity.italy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.footballapp.repository.FootballRepository

class ItalyViewModelFactory(
    private val repository: FootballRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItalyViewModel(repository) as T
    }
}