package com.example.footballapp.repository

import com.example.footballapp.network.ApiService

class FootballRepository(private val apiService: ApiService) {
    suspend fun getAllLeagues() = apiService.getAllLeagues()
    suspend fun getClubsId(id: String) = apiService.getClubs(id)
    suspend fun getEngland(id: String) = apiService.getEngland(id)
}