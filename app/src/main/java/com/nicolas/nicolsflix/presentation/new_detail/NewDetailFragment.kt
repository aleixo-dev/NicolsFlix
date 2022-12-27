package com.nicolas.nicolsflix.presentation.new_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.common.Constants
import com.nicolas.nicolsflix.common.loadImage
import com.nicolas.nicolsflix.common.showToast
import com.nicolas.nicolsflix.databinding.NewDetailFragmentBinding
import com.nicolas.nicolsflix.network.models.remote.CastFromMovie
import com.nicolas.nicolsflix.network.models.remote.MovieDetailsResponse
import com.nicolas.nicolsflix.presentation.detail.adpter.CastAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewDetailFragment : Fragment() {

    private var _binding: NewDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewDetailViewModel by viewModel()
    private val arguments: NewDetailFragmentArgs by navArgs()
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEvent(NewDetailEvent.OnOpened(arguments.movie.id))
        render()
    }

    private fun render() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                NewDetailState.Loading -> {}
                NewDetailState.Error -> {}
                is NewDetailState.SetupViews -> {
                    setupViews(state.cast, state.details)
                }
            }
        }
    }

    private fun setupViews(casts: List<CastFromMovie>?, detailsResponse: MovieDetailsResponse?) {
        binding.apply {
            setupBackgroundMovie()
            setupListeners()
            setupText()
            setupRatingBar()
            setupRecyclerView(casts)
        }
    }

    private fun NewDetailFragmentBinding.setupRecyclerView(castFromMovie: List<CastFromMovie>?) {
        recyclerViewCastNewDetailMovie.apply {
            castFromMovie?.let {
                adapter = CastAdapter(casts = it) {
                    // todo navigation to cast details screen.
                }
            }
        }
    }

    private fun NewDetailFragmentBinding.setupText() {
        textViewMovieDetailTitle.text = arguments.movie.title.orEmpty()
        textViewMovieDetailDescription.text = arguments.movie.description ?: ""
    }

    private fun NewDetailFragmentBinding.setupListeners() {
        imgArrowBack.setOnClickListener { findNavController().popBackStack() }
        imgFavoriteMovie.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) imgFavoriteMovie.setImageResource(R.drawable.ic_round_favorited_24)
            else imgFavoriteMovie.setImageResource(
                R.drawable.ic_round_favorite_border_24
            )
        }
    }

    private fun NewDetailFragmentBinding.setupRatingBar() {
        arguments.movie.rating?.let {
            ratingBarMovieDetail.rating = it.toFloat() / RATING_DIVIDER
        }
    }

    private fun NewDetailFragmentBinding.setupBackgroundMovie() {
        imgBackgroundMovie.loadImage(
            imgBackgroundMovie.context,
            "${Constants.LOAD_IMAGE_URL}${arguments.movie.posterDetails}"
        )
    }

    private fun setEvent(newEvent: NewDetailEvent) {
        viewModel.interact(newEvent)
    }

    companion object {
        const val RATING_DIVIDER = 2
    }
}