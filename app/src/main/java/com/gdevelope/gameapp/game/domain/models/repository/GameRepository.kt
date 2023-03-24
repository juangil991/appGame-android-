package com.gdevelope.gameapp.game.domain.models.repository

import com.gdevelope.gameapp.game.domain.models.Game

interface GameRepository {
    suspend fun getAllGames(): List<Game>
    suspend fun getGame(id: Int): Game
}