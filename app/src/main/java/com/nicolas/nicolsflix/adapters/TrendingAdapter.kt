package com.nicolas.nicolsflix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.nicolsflix.common.Constants.LOAD_IMAGE_URL
import com.nicolas.nicolsflix.common.loadImage
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.databinding.NewItemsRecyclerViewBinding

class TrendingAdapter(
    private val listMovie: List<Movie>,
    private val onItemClick: (movie: Movie) -> Unit,
) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    var movieName: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view =
            NewItemsRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return TrendingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount() = listMovie.size

    inner class TrendingViewHolder(
        private val binding: NewItemsRecyclerViewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                imgPosterMovie.loadImage(
                    imgPosterMovie.context,
                    "$LOAD_IMAGE_URL${movie.poster}"
                )

                movie.title?.let { titleMovie -> tvNameMovie.text = titleMovie }
                movie.rating?.let { rating ->
                    ratingBarMovie.rating = rating.toFloat() / RATING_BAR_DIVIDER
                }

                itemView.setOnClickListener {
                    onItemClick.invoke(movie)
                }
            }
        }
    }

    companion object {
        const val RATING_BAR_DIVIDER = 2
    }
}
