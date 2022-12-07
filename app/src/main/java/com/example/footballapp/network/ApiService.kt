package com.example.footballapp.network

import com.example.footballapp.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?action=get_countries")
    suspend fun getAllLeagues(
        @Query("APIkey") key: String = "93f7b64060f76a0273144955d104c8f7fbf4ebc038627759bfa804f883fecee1"
    ): Response<FootballDTO>

    @GET("?action=get_standings")
    suspend fun getClubs(
        @Query("league_id") id: String = "302",
        @Query("APIkey") key: String = "93f7b64060f76a0273144955d104c8f7fbf4ebc038627759bfa804f883fecee1"
    ): Response<ClubsDTO>

    @GET("?action=get_standings")
    suspend fun getEngland(
        @Query("league_id") id: String = "152",
        @Query("APIkey") key: String = "93f7b64060f76a0273144955d104c8f7fbf4ebc038627759bfa804f883fecee1"
    ): Response<EnglandDTO>

    @GET("?action=get_standings")
    suspend fun getGermany(
        @Query("league_id") id: String = "175",
        @Query("APIkey") key: String = "93f7b64060f76a0273144955d104c8f7fbf4ebc038627759bfa804f883fecee1"
    ): Response<GermanyDTO>

    @GET("?action=get_standings")
    suspend fun getFrance(
        @Query("league_id") id: String = "168",
        @Query("APIkey") key: String = "93f7b64060f76a0273144955d104c8f7fbf4ebc038627759bfa804f883fecee1"
    ): Response<FranceDTO>

    @GET("?action=get_standings")
    suspend fun getItaly(
        @Query("league_id") id: String = "207",
        @Query("APIkey") key: String = "93f7b64060f76a0273144955d104c8f7fbf4ebc038627759bfa804f883fecee1"
    ): Response<ItalyDTO>


}