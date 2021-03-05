package com.nicolas.nicolsflix.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.data.model.MovieTrending
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items_recycler_home.view.*

class TrendingAdapter(
    private val listMovie: ArrayList<Movie>,
    private val onMovieTrendingClickListener: ((movie: Movie) -> Unit)
) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_recycler_home, parent, false)
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

        private val imgHomeView: ImageView = itemView.imageViewHomeView

        fun bind(movie: Movie) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/${movie.poster}")
                .into(imgHomeView)
            itemView.textViewHomeRating.text = movie.rating
            itemView.textViewNameHome.text = movie.title

            itemView.setOnClickListener {
                onMovieTrendingClickListener.invoke(movie)
            }
        }
    }
}
