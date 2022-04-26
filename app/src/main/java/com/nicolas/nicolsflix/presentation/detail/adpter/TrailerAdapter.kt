package com.nicolas.nicolsflix.presentation.detail.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.nicolsflix.databinding.LayoutTrailerItemsBinding
import com.nicolas.nicolsflix.network.models.remote.Trailers
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class TrailerAdapter(
    private val trailers: List<Trailers>
) : RecyclerView.Adapter<TrailerAdapter.MainViewHolder>() {

    class MainViewHolder(
        binding: LayoutTrailerItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val trailerVideo = binding.youtubePlayerTrailerVideo

        fun bind(trailers: Trailers) {

            itemView.findViewTreeLifecycleOwner()?.lifecycle?.addObserver(trailerVideo)

            trailerVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(trailers.key, 0f)
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutTrailerItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(trailers[position])
    }

    override fun getItemCount() = trailers.size
}