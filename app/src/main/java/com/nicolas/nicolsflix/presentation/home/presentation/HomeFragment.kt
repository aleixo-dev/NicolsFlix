package com.nicolas.nicolsflix.presentation.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.nicolas.nicolsflix.adapters.RecyclerSearchAdapter
import com.nicolas.nicolsflix.adapters.TrendingAdapter
import com.nicolas.nicolsflix.databinding.HomeFragmentBinding
import com.nicolas.nicolsflix.common.toLowerCase
import com.nicolas.nicolsflix.data.model.Movie
import com.nicolas.nicolsflix.upcoming.utils.DataState
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.nicolas.nicolsflix.presentation.home.presentation.HomeViewModel.State

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        render()
        setEvent(HomeEvent.OnOpened)
        setRecyclerViewSearch()
        setupTextSearch()
        getMoviePopular()
        setupMovieTrending()
    }

    private fun loadStateScreen() {
        viewModel.state.observe(viewLifecycleOwner) { state ->

        }
    }

    private fun screenRender() {
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                HomeState.Loading -> {}
                is HomeState.Error -> {}
                is HomeState.Success -> {}
            }
        }
    }

    private fun render() = binding.apply {
        viewModel.apply {
            listPopularMovie.observe(viewLifecycleOwner) { populars ->
                setupPopularMovie(populars)
            }
            listTrendingMovie.observe(viewLifecycleOwner) { trending ->
                setupTrendingMovie(trending)
            }
        }
    }

    private fun HomeFragmentBinding.setupPopularMovie(populars: List<Movie>) {
        moviePopularRecyclerView.adapter = TrendingAdapter(populars,
            onItemClick = { navigateNext(it) }
        )
    }

    private fun HomeFragmentBinding.setupTrendingMovie(trending: List<Movie>) {
        movieTrendingRecyclerView.adapter = TrendingAdapter(trending,
            onItemClick = { navigateNext(it) }
        )
    }

    private fun navigateNext(movie: Movie) {
        val directions =
            HomeFragmentDirections.actionHomeNavigationToDetailFragment(movie)
        findNavController().navigate(directions)
    }

    private fun getMoviePopular() {
        viewModel.fetchPopularMovie()
        viewModel.moviePopularList.observe(viewLifecycleOwner) { movie ->
            when (movie) {
                is DataState.Loading -> {
                    // TODO implement loading.
                }
                is DataState.Success -> {
                    // TODO show popular and trending, use fun:
                    /** @setVisible() */
                }
                is DataState.Error -> {
                    // TODO show layout error
                }
                is DataState.Empty -> {
                }
            }
        }
    }

    private fun setRecyclerViewSearch() {
        binding.movieSearchRecyclerView.run {
            viewModel.listNamesMovie.observe(viewLifecycleOwner) {
                setHasFixedSize(true)
                if (it.isEmpty()) {
                    setVisible(movieNotFound = true)
                } else {
                    adapter = RecyclerSearchAdapter(it) { onClickMovie ->
                        val directions =
                            HomeFragmentDirections.actionHomeNavigationToDetailFragment(onClickMovie)
                        findNavController().navigate(directions)
                    }
                }
            }
        }
    }

    private fun setupMovieTrending() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                State.Loading -> {}
                is State.Error -> {}
                is State.Success -> {
                    binding.setupTrendingMovie(it.movies)
                }
            }
        }
    }

    private fun setupTextSearch() {
        binding.include.editSearchMovieHome.addTextChangedListener { movieName ->
            if (!movieName.isNullOrEmpty()) {
                getMoviePerName(movieName.toString())
                setVisible(recyclerSearchMovies = true)
            } else setVisible(containerMovies = true)
        }
    }

    private fun setVisible(
        containerMovies: Boolean = false,
        recyclerSearchMovies: Boolean = false,
        movieNotFound: Boolean = false,
    ) {
        with(binding) {
            linearLayoutContainerMovies.isVisible = containerMovies
            movieSearchRecyclerView.isVisible = recyclerSearchMovies
            tvMovieNotFound.isVisible = movieNotFound
        }
    }

    private fun getMoviePerName(name: String) {
        viewModel.fetchNameMovie(toLowerCase(name))
    }

    private fun setEvent(newEvent: HomeEvent) {
        viewModel.interact(newEvent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}