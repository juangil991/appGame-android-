package com.gdevelope.gameapp.game.ui.gamedetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gdevelope.gameapp.R
import com.gdevelope.gameapp.databinding.FragmentGameBinding
import com.gdevelope.gameapp.databinding.FragmentGameDetailsBinding
import com.gdevelope.gameapp.game.domain.models.repository.GameRepositoryImpl
import com.gdevelope.gameapp.game.domain.resources.Resource
import com.gdevelope.gameapp.game.infrastructure.data.remote.GamesDataSource
import com.gdevelope.gameapp.game.infrastructure.service.RetrofitClient
import com.gdevelope.gameapp.game.presentation.GameViewModel
import com.gdevelope.gameapp.game.presentation.GameViewModelFactory
import com.gdevelope.gameapp.game.ui.gamedetail.adapter.GameDetailsAdapter
import com.gdevelope.gameapp.game.ui.games.adapter.GamesAdapter


class GameDetailsFragment : Fragment(R.layout.fragment_game_details) {

    private lateinit var binding : FragmentGameDetailsBinding
    private val args by navArgs<GameDetailsFragmentArgs>()
    private lateinit var adapter: GameDetailsAdapter
    private val viewModel by viewModels<GameViewModel> {
        GameViewModelFactory(
            GameRepositoryImpl(
                GamesDataSource(RetrofitClient.webService)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameDetailsBinding.bind(view)
        viewModel.fetchGame(args.id).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    Glide.with(requireContext()).load(result.data.thumbnail).into(binding.imgGame)
                    binding.tvGameTitle.text = result.data.title
                    binding.tvStatus.text = result.data.status
                    binding.tvGenre.text = result.data.genre
                    binding.tvPlatform.text = result.data.platform
                    binding.tvPublisher.text = result.data.publisher
                    binding.tvDescription.text = result.data.short_description
                    adapter = GameDetailsAdapter(result.data.screenshots)
                    binding.rvScreenshots.adapter = adapter
                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })


    }
}