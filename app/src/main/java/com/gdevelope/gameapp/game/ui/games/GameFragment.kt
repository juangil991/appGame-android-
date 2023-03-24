package com.gdevelope.gameapp.game.ui.games

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gdevelope.gameapp.R
import com.gdevelope.gameapp.databinding.FragmentGameBinding
import com.gdevelope.gameapp.game.domain.models.Game
import com.gdevelope.gameapp.game.domain.models.repository.GameRepositoryImpl
import com.gdevelope.gameapp.game.domain.resources.Resource
import com.gdevelope.gameapp.game.infrastructure.data.remote.GamesDataSource
import com.gdevelope.gameapp.game.infrastructure.service.RetrofitClient
import com.gdevelope.gameapp.game.presentation.GameViewModel
import com.gdevelope.gameapp.game.presentation.GameViewModelFactory
import com.gdevelope.gameapp.game.ui.games.adapter.GamesAdapter

class GameFragment : Fragment(R.layout.fragment_game), GamesAdapter.OnGameClickListener {

    private lateinit var binding: FragmentGameBinding

    private lateinit var Adapter: GamesAdapter

    private val viewModel by viewModels<GameViewModel> {
        GameViewModelFactory(
            GameRepositoryImpl(
                GamesDataSource(RetrofitClient.webService)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameBinding.bind(view)

        viewModel.fetchAllGames().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Adapter = GamesAdapter(result.data, this@GameFragment)
                    binding.rvGames.adapter = Adapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })
    }

    override fun onGameClick(game: Game) {
        val action = GameFragmentDirections.actionGameFragmentToGameDetailsFragment(game.id)
        findNavController().navigate(action)
    }
}