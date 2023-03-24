package com.gdevelope.gameapp.game.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.gdevelope.gameapp.game.domain.models.repository.GameRepository
import com.gdevelope.gameapp.game.domain.resources.Resource
import kotlinx.coroutines.Dispatchers

class GameViewModel(private val repository: GameRepository) : ViewModel() {

    fun fetchAllGames() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getAllGames()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchGame(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getGame(id)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

}

class GameViewModelFactory(private val repository: GameRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GameRepository::class.java).newInstance(repository)
    }
}