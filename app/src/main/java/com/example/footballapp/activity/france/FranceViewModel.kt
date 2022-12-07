package com.example.footballapp.activity.france

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.repository.FootballRepository
import kotlinx.coroutines.launch

class FranceViewModel(
    private val repository: FootballRepository
) : ViewModel() {
    private val _state: MutableLiveData<FranceState> = MutableLiveData()
    val state: LiveData<FranceState> get() = _state

    fun getClubById(id: String) {
        viewModelScope.launch {
            _state.postValue(FranceState.Loading)
            try {
                val response = repository.getFrance(id)
                if (response.isSuccessful) {
                    _state.postValue(FranceState.Success(response.body()!!))

                }
            } catch (e: Exception) {
                _state.postValue(FranceState.Error(e.message!!))
            }
        }
    }

}