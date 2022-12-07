package com.example.footballapp.activity.italy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.repository.FootballRepository
import kotlinx.coroutines.launch

class ItalyViewModel(
    private val repository: FootballRepository
) : ViewModel() {
    private val _state: MutableLiveData<ItalyState> = MutableLiveData()
    val state: LiveData<ItalyState> get() = _state

    fun getClubById(id: String) {
        viewModelScope.launch {
            _state.postValue(ItalyState.Loading)
            try {
                val response = repository.getItaly(id)
                if (response.isSuccessful) {
                    _state.postValue(ItalyState.Success(response.body()!!))

                }
            } catch (e: Exception) {
                _state.postValue(ItalyState.Error(e.message!!))
            }
        }
    }

}