package com.nicolas.nicolsflix.upcoming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicolas.nicolsflix.databinding.ItemsLayoutUpcomingBinding
import com.nicolas.nicolsflix.upcoming.domain.model.UpcomingUiDomain
import com.squareup.picasso.Picasso

class UpcomingAdapter(
    private val listMovieUpcoming: List<UpcomingUiDomain>,
    private val clickMovie: ((movie: UpcomingUiDomain) -> Unit)
) : RecyclerView.Adapter<UpcomingAdapter.ViewHolder>() {
    class ViewHolder(
        binding: ItemsLayoutUpcomingBinding,
        private val clickMovie: ((movie: UpcomingUiDomain) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        private val imgMovieUpcoming: ImageView = binding.imageMovie
        private val tvNameMovieUpcoming: TextView = binding.tvNameMovieUp
        private val tvDescription: TextView = binding.tvDescriptionNameUp

        fun bind(upcomingUiDomain: UpcomingUiDomain) {
            tvNameMovieUpcoming.text = upcomingUiDomain.title
            tvDescription.text = upcomingUiDomain.description
            Picasso.get().load("https://image.tmdb.org/t/p/w500/${upcomingUiDomain.imagePoster}")
                .into(imgMovieUpcoming)
            itemView.setOnClickListener {
                clickMovie.invoke(upcomingUiDomain)
            }
            // loadImageUrl(imgMovieUpcoming, upcomingUiDomain.imagePoster)
        }

        private fun loadImageUrl(imageView: ImageView, url: String?) {
            url?.let {
                val imageUri = it.toUri().buildUpon().scheme("https").build()
                Glide.with(imageView.context)
                    .load("https://image.tmdb.org/t/p/w500/${imageUri}")
                    .centerCrop()
                    .into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout =
            ItemsLayoutUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(layout, clickMovie)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovieUpcoming[position])
    }

    override fun getItemCount() = listMovieUpcoming.size
}