package com.gdevelope.gameapp.game.infrastructure.data.remote

import com.gdevelope.gameapp.game.domain.models.Game
import com.gdevelope.gameapp.game.infrastructure.service.WebService

class GamesDataSource(private val webService: WebService) {

    suspend fun getAllGames(): List<Game> = webService.getAllGames()

    suspend fun getGame(id : Int): Game = webService.getGame(id)
}