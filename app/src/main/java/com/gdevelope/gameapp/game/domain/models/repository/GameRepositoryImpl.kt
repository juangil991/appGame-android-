package com.gdevelope.gameapp.game.domain.models.repository

import com.gdevelope.gameapp.game.domain.models.Game
import com.gdevelope.gameapp.game.infrastructure.data.remote.GamesDataSource

class GameRepositoryImpl(private val dataSource: GamesDataSource) : GameRepository {

    override suspend fun getAllGames(): List<Game> = dataSource.getAllGames()

    override suspend fun getGame(id: Int): Game = dataSource.getGame(id)
}