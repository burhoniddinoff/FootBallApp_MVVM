package com.example.footballapp.activity.germany

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.footballapp.repository.FootballRepository

class GermanyViewModelFactory(
    private val repository: FootballRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GermanyViewModel(repository) as T
    }
}