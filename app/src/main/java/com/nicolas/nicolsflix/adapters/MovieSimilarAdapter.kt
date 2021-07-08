package com.nicolas.nicolsflix.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items_list_movies.view.*

class MovieSimilarAdapter(
    private val listMovie: List<Movie>,
    private val movieClickListener: ((movie: Movie) -> Unit)
) :
    RecyclerView.Adapter<MovieSimilarAdapter.SimilarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_list_movies, parent, false)
        return SimilarViewHolder(view, movieClickListener)
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount() = listMovie.size

    class SimilarViewHolder(
        itemView: View,
        private val movieClickListener: ((movie: Movie) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        private val imgRecommend: ImageView = itemView.imgRecommend

        fun bind(movie: Movie) {

            Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie.poster}")
                .into(imgRecommend)

            itemView.setOnClickListener {
                movieClickListener.invoke(movie)

            }
        }
    }
}