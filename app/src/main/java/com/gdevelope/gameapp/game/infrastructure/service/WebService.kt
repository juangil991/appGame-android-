package com.gdevelope.gameapp.game.infrastructure.service

import com.gdevelope.gameapp.game.domain.models.Game
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("games")
    suspend fun getAllGames(): List<Game>

    @GET("game")
    suspend fun getGame(@Query("id") id: Int): Game
}