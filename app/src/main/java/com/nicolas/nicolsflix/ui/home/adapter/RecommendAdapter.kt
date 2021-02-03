package com.nicolas.nicolsflix.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items_list_movies.view.*

class RecommendAdapter(
    private val listMovieRecommend: List<Movie>,
    private val movieRecommendClick: ((movie: Movie) -> Unit)
) : RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_list_movies, parent, false)
        return RecommendViewHolder(view, movieRecommendClick)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.bind(listMovieRecommend[position])
    }

    override fun getItemCount() = listMovieRecommend.size

    class RecommendViewHolder(
        itemView: View,
        private val movieRecommendClick: ((movie: Movie) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        private val imgRecommend: ImageView = itemView.imgRecommend

        fun bind(movie: Movie) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie.poster}").into(imgRecommend)
            itemView.setOnClickListener {
                movieRecommendClick.invoke(movie)
            }
        }
    }
}