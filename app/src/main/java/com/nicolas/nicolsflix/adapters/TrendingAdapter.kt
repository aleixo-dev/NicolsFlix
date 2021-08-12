package com.nicolas.nicolsflix.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items_recycler_home.view.*
import kotlinx.android.synthetic.main.new_items_recycler_view.view.*

class TrendingAdapter(
    private val listMovie: ArrayList<Movie>,
    private val onMovieTrendingClickListener: ((movie: Movie) -> Unit)
) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_items_recycler_view, parent, false)
        return TrendingViewHolder(view, onMovieTrendingClickListener)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount() = listMovie.size

    class TrendingViewHolder(
        itemView: View,
        private val onMovieTrendingClickListener: ((movie: Movie) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        // private val imgHomeView: ImageView = itemView.imageTrendingView

        /** Implementações do novo layout.       */

        private val imgPosterMovie: ImageView = itemView.imgPosterMovie
        private val tvNameMovie: TextView = itemView.tvNameMovie
        private val ratingBarMovie: RatingBar = itemView.ratingBarMovie

        fun bind(movie: Movie) {

            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/${movie.poster}")
                .into(imgPosterMovie)

            if(!movie.title.equals("")) {
                tvNameMovie.text = movie.title
            }

            if (movie.rating != null) {
                val rating = movie.rating.toFloat() / 2
                ratingBarMovie.rating = rating
            }

            itemView.setOnClickListener {
                onMovieTrendingClickListener.invoke(movie)
            }
        }
    }
}
