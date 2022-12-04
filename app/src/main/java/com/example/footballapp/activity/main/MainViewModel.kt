package com.example.footballapp.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.repository.FootballRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: FootballRepository
) : ViewModel() {
    private val _state: MutableLiveData<MainState> = MutableLiveData()
    val state: LiveData<MainState> get() = _state

    init {
        getAllLeagues()
    }

    private fun getAllLeagues() {
        viewModelScope.launch {
            _state.postValue(MainState.Loading)
            try {
                val response = repository.getAllLeagues()
                if (response.isSuccessful) {
                    _state.postValue(MainState.Success(response.body()!!))
                }
            } catch (e: Exception) {
                _state.postValue(MainState.Error(e.message!!))
            }
        }
    }

}