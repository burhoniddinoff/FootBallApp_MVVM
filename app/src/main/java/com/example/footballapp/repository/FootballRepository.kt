package com.example.footballapp.repository

import com.example.footballapp.network.ApiService

class FootballRepository(private val apiService: ApiService) {
    suspend fun getAllLeagues() = apiService.getAllLeagues()
    suspend fun getClubsId(id: String) = apiService.getClubs(id)
    suspend fun getEngland(id: String) = apiService.getEngland(id)
    suspend fun getGermany(id: String) = apiService.getGermany(id)
    suspend fun getFrance(id: String) = apiService.getFrance(id)
    suspend fun getItaly(id: String) = apiService.getItaly(id)
}