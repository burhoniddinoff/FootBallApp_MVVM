package com.example.footballapp.activity.germany

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.repository.FootballRepository
import kotlinx.coroutines.launch

class GermanyViewModel(
    private val repository: FootballRepository
) : ViewModel() {
    private val _state: MutableLiveData<GermanyState> = MutableLiveData()
    val state: LiveData<GermanyState> get() = _state

    fun getClubById(id: String) {
        viewModelScope.launch {
            _state.postValue(GermanyState.Loading)
            try {
                val response = repository.getGermany(id)
                if (response.isSuccessful) {
                    _state.postValue(GermanyState.Success(response.body()!!))

                }
            } catch (e: Exception) {
                _state.postValue(GermanyState.Error(e.message!!))
            }
        }
    }

}