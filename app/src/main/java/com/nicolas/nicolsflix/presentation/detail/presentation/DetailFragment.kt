package com.nicolas.nicolsflix.presentation.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.platform.*
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.common.Constants
import com.nicolas.nicolsflix.databinding.DetailFragmentBinding
import com.nicolas.nicolsflix.network.models.remote.CastFromMovie
import com.nicolas.nicolsflix.presentation.detail.adpter.CastAdapter
import com.nicolas.nicolsflix.common.LoadImage
import com.nicolas.nicolsflix.common.loadImage
import com.nicolas.nicolsflix.common.showToast
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
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward = */ true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward = */ false)
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
        setupMovieInfo()
        observerChangeInViewModel()
        setupArgumentsScreen()
    }

    private fun setupArgumentsScreen() {
        arguments.movie.id?.let {
            viewModel.apply {
                getCastMovieDetail(it)
                getTrailerVideo(it)
            }
        }
    }

    private fun observerChangeInViewModel() {
        viewModel.apply {
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
                    navigateNext(onCastClick.id)
                }
            }
        }
    }

    private fun navigateNext(movieId: Int) {
        val directions =
            DetailFragmentDirections.actionDetailFragmentToCastFragment(movieId)
        findNavController().navigate(directions)
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

    private fun setupMovieInfo() = context?.let { ctx ->
        binding.apply {
            imageViewBackgroundMovie.loadImage(
                imageViewBackgroundMovie.context,
                "${Constants.LOAD_IMAGE_URL}${arguments.movie.posterDetails}"
            )
            textViewNameMovie.text = arguments.movie.title
            textViewMovieDescription.text = arguments.movie.description
            textViewMovieRate.text = arguments.movie.rating
        }
    }
}