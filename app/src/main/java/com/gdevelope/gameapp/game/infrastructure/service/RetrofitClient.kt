package com.gdevelope.gameapp.game.infrastructure.service

import com.gdevelope.gameapp.game.domain.util.GameConstants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RetrofitClient {
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl(GameConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }
}