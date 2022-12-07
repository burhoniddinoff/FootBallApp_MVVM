package com.example.footballapp.activity.germany

import com.example.footballapp.model.England
import com.example.footballapp.model.Germany

sealed class GermanyState {
    object Loading: GermanyState()
    data class Error(val error: String): GermanyState()
    data class Success(val list: List<Germany>): GermanyState()
}