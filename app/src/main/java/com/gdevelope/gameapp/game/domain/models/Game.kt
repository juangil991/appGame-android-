package com.gdevelope.gameapp.game.domain.models

data class Game(
    val id: Int = -1,
    val title: String = "",
    val thumbnail: String = "",
    val status: String = "",
    val short_description: String = "",
    val description: String = "",
    val game_url: String = "",
    val genre: String = "",
    val platform: String = "",
    val publisher: String = "",
    val developer: String = "",
    val release_date: String = "",
    val freetogame_profile_url: String = "",
    val minimum_system_requirements: Requirements = Requirements(),
    val screenshots : List<Screenshots> = emptyList()
    )