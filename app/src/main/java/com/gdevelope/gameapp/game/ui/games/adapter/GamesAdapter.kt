package com.gdevelope.gameapp.game.ui.games.adapter

import android.content.Context
import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gdevelope.gameapp.databinding.MainGameItemBinding
import com.gdevelope.gameapp.game.domain.models.Game
import com.gdevelope.gameapp.game.domain.util.BaseViewHolder

class GamesAdapter(
    private val gameList: List<Game>,
    private val itemOnClickListener: OnGameClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnGameClickListener {
        fun onGameClick(game: Game)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            MainGameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = GamesViewHolder(itemBinding, parent.context)
        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            } ?: return@setOnClickListener
            itemOnClickListener.onGameClick(gameList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is GamesViewHolder -> holder.bind(gameList[position])
        }
    }

    override fun getItemCount(): Int = gameList.size

    private inner class GamesViewHolder(val binding: MainGameItemBinding, val context: Context) :
        BaseViewHolder<Game>(binding.root) {
        override fun bind(item: Game) {
            Glide.with(context).load(item.thumbnail).centerCrop().into(binding.imgGame)
            binding.tvStatus.text = item.status
            binding.tvGenre.text = item.genre
            binding.tvPlatform.text = item.platform
            binding.tvPublisher.text = item.publisher
        }
    }
}