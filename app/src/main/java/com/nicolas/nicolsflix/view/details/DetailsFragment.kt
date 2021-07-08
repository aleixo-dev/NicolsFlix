package com.nicolas.nicolsflix.view.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.adapters.MovieSimilarAdapter
import com.nicolas.nicolsflix.utils.FormatDate
import com.nicolas.nicolsflix.viewmodel.DetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment(R.layout.details_fragment) {

    private val arguments: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponentsDetails()
    }

    private fun initComponentsDetails() {

        val detailsMovieArgs = arguments.movie
        backDetails.setOnClickListener {
            findNavController().popBackStack()
        }

        textNameMovie.text = detailsMovieArgs.title
        textValueRatingMovie.text = detailsMovieArgs.rating
        textDateMovie.text = detailsMovieArgs.date?.let { FormatDate.getDateMovie(it) }

        textDescriptionMovie.text = detailsMovieArgs.description
        Picasso.get().load("https://image.tmdb.org/t/p/w500/${detailsMovieArgs.posterDetails}")
            .into(imagePosterMovie)

        saveMovieToList(detailsMovieArgs)

        detailsMovieArgs.id.let {
            if (it != null) {
                viewModel.getMovieSimilar(it)
            }
        }

        viewModel.listMovieSimilar.observe(viewLifecycleOwner) { listMovie ->

            if (listMovie.isNullOrEmpty()) {
                movieSimiliarRecyclerView.visibility = View.GONE
                textSimilar.visibility = View.GONE
            } else {
                with(movieSimiliarRecyclerView) {
                    layoutManager =
                        LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = MovieSimilarAdapter(listMovie) {
                        val directions = DetailsFragmentDirections.actionDetailsFragmentSelf(it)
                        findNavController().navigate(directions)
                    }
                }
            }
        }
    }

    private fun saveMovieToList(movie: Movie) {

        viewModel.isMovieMyList(movie)
        viewModel.isMovieMyList.observe(viewLifecycleOwner) { isMovieSave ->
            if (isMovieSave) {

                imageSaveMovie.setOnClickListener(deleteMovieMyList(movie))
                imageSaveMovie.setImageResource(R.drawable.ic_round_check)

            } else {
                imageSaveMovie.setOnClickListener(addMovieMyList(movie))
                imageSaveMovie.setImageResource(R.drawable.ic_round_add)
            }
        }
    }

    private fun addMovieMyList(movie: Movie) = View.OnClickListener {
        viewModel.insertMovieMyList(movie)
        imageSaveMovie.setImageResource(R.drawable.ic_round_check)
        Toast.makeText(requireContext(), "Filme salvo na sua lista.", Toast.LENGTH_SHORT).show()
    }

    private fun deleteMovieMyList(movie: Movie) = View.OnClickListener {
        viewModel.deleteMovieMyList(movie)
        imageSaveMovie.setImageResource(R.drawable.ic_round_add)
        Toast.makeText(requireContext(), "Filme removido da sua lista.", Toast.LENGTH_SHORT).show()
    }
}