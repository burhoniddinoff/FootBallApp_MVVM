package com.example.footballapp.activity.italy

import com.example.footballapp.model.France
import com.example.footballapp.model.Italy

sealed class ItalyState {
    object Loading: ItalyState()
    data class Error(val error: String): ItalyState()
    data class Success(val list: List<Italy>): ItalyState()
}