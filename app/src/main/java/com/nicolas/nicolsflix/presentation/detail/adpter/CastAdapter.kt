package com.nicolas.nicolsflix.presentation.detail.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nicolas.nicolsflix.common.loadImage
import com.nicolas.nicolsflix.databinding.CastMovieLayoutDetailBinding
import com.nicolas.nicolsflix.network.models.remote.CastFromMovie
import com.squareup.picasso.Picasso

class CastAdapter(
    private val casts: List<CastFromMovie>,
    private val onCastItemClick: ((cast: CastFromMovie) -> Unit)
) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    class CastViewHolder(
        private var binding: CastMovieLayoutDetailBinding,
        private val onCastItemClick: ((cast: CastFromMovie) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        private val imageCharacter = binding.imageViewCharacterMovieDetail

        fun bind(casts: CastFromMovie) {
            imageCharacter.loadImage(
                itemView.context,
                "https://image.tmdb.org/t/p/original/${casts.profilePath}"
            )
            itemView.setOnClickListener {
                onCastItemClick.invoke(casts)
            }

            binding.textViewCastNameNewDetail.text = casts.name
            binding.textViewCharacterNewDetail.text = casts.character ?: "houve um erro"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layout = CastMovieLayoutDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CastViewHolder(layout, onCastItemClick)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(casts[position])
    }

    override fun getItemCount() = casts.size

}