package com.example.footballapp.activity.england

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.activity.league.LeagueState
import com.example.footballapp.repository.FootballRepository
import kotlinx.coroutines.launch

class EnglandViewModel(
    private val repository: FootballRepository
) : ViewModel() {
    private val _state: MutableLiveData<EnglandState> = MutableLiveData()
    val state: LiveData<EnglandState> get() = _state

    fun getClubById(id: String) {
        viewModelScope.launch {
            _state.postValue(EnglandState.Loading)
            try {
                val response = repository.getEngland(id)
                if (response.isSuccessful) {
                    _state.postValue(EnglandState.Success(response.body()!!))

                }
            } catch (e: Exception) {
                _state.postValue(EnglandState.Error(e.message!!))
            }
        }
    }

}