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

class MyListAdapter(
    private val listMovie: List<Movie>,
    private val itemMovieClickListener: ((movie: Movie) -> Unit)
) :
    RecyclerView.Adapter<MyListAdapter.MyListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_list_movies, parent, false)
        return MyListViewHolder(view, itemMovieClickListener)
    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount() = listMovie.size

    class MyListViewHolder(itemView: View, private val itemMovieClickListener: ((movie: Movie) -> Unit)) : RecyclerView.ViewHolder(itemView) {

        private val imgRecommend: ImageView = itemView.imgRecommend

        fun bind(movie: Movie) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie.poster}").into(imgRecommend)

            itemView.setOnClickListener {
                itemMovieClickListener.invoke(movie)
            }

        }
    }
}