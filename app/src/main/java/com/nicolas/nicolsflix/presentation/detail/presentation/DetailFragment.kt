package com.nicolas.nicolsflix.presentation.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.platform.*
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.common.Constants
import com.nicolas.nicolsflix.databinding.DetailFragmentBinding
import com.nicolas.nicolsflix.network.models.remote.CastFromMovie
import com.nicolas.nicolsflix.presentation.detail.adpter.CastAdapter
import com.nicolas.nicolsflix.upcoming.utils.LoadImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val arguments: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward= */ true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward= */ false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTopAppBar()
        setupMovie()
        observerChangeInViewModel()
        setupArgumentsScreen()
    }

    private fun setupArgumentsScreen() {
        arguments.movie.id?.let {
            viewModel.run {
                getCastMovieDetail(it)
                getTrailerVideo(it)
            }
        }
    }

    private fun observerChangeInViewModel() {
        viewModel.run {
            casts.observe(viewLifecycleOwner) { casts ->
                setupRecyclerViewCasts(casts)
            }
            trailers.observe(viewLifecycleOwner) { trailers ->
                setupTrailers(trailers[0].key)
            }
        }
    }

    private fun setupTrailers(videoKey: String) {
        binding.apply {
            lifecycle.addObserver(youtubeVideoCastTrailer)
            youtubeVideoCastTrailer.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(videoKey, 0f)
                    youTubePlayer.pause()
                }
            })
        }
    }

    private fun setupRecyclerViewCasts(casts: List<CastFromMovie>) {
        with(binding) {
            recyclerViewCastMovieDetail.apply {
                adapter = CastAdapter(casts) { onCastClick ->
                    // TODO: open cast screen detail when click.
                }
                setHasFixedSize(true)
            }
        }
    }

    private fun setupTopAppBar() {
        binding.topAppBar.apply {
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.favorite_icon -> true
                    R.id.share_icon -> true
                    else -> false
                }
            }

            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setupMovie() {
        binding.apply {
            context?.let { context ->
                LoadImage.load(
                    context,
                    Constants.LOAD_IMAGE_URL + "${arguments.movie.posterDetails}",
                    imageViewBackgroundMovie
                )
            }

            textViewNameMovie.text = arguments.movie.title
            textViewMovieDescription.text = arguments.movie.description
            textViewMovieRate.text = arguments.movie.rating
        }
    }
}