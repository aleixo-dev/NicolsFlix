package com.nicolas.nicolsflix.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items_search_movie.view.*

class RecyclerSearchAdapter(
    private val listMovieSearch: List<Movie>,
    private val movieClickListener: ((movie: Movie) -> Unit)
) :
    RecyclerView.Adapter<RecyclerSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_search_movie, parent, false)
        return ViewHolder(view, listMovieSearch, movieClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovieSearch[position])
    }

    override fun getItemCount() = listMovieSearch.size

    class ViewHolder(
        itemView: View,
        listMovieSearch: List<Movie>?,
        private val movieClickListener: ((movie: Movie) -> Unit)
    ) :
        RecyclerView.ViewHolder(itemView) {

        /*
        init {
            if (listMovieSearch != null) {
                itemView.setOnClickListener {
                    val p = adapterPosition
                    if (p != RecyclerView.NO_POSITION) {
                        movieClickListener.invoke(listMovieSearch[p])
                    }
                }
            }
        }

         */

        fun bind(searchMovie: Movie) {
            val imagePoster = itemView.imageSearch
            val rating = itemView.searchRating

            Picasso.get().load("https://image.tmdb.org/t/p/w500/${searchMovie.poster}")
                .into(imagePoster)

            rating.text = searchMovie.rating

            itemView.setOnClickListener {
                movieClickListener.invoke(searchMovie)
            }
        }
    }
}