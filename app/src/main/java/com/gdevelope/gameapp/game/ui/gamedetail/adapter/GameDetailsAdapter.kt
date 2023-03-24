package com.gdevelope.gameapp.game.ui.gamedetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gdevelope.gameapp.databinding.GameItemBinding
import com.gdevelope.gameapp.game.domain.models.Screenshots
import com.gdevelope.gameapp.game.domain.util.BaseViewHolder

class GameDetailsAdapter(private val screenshots: List<Screenshots>) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is GamesViewHolder -> holder.bind(screenshots[position])
        }
    }

    override fun getItemCount(): Int = screenshots.size

    private inner class GamesViewHolder(val binding: GameItemBinding, val context: Context) :
        BaseViewHolder<Screenshots>(binding.root) {
        override fun bind(item: Screenshots) {
            Glide.with(context).load(item.image).centerCrop().into(binding.imgGame)

        }
    }
}