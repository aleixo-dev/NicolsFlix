package com.nicolas.nicolsflix.presentation.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.nicolas.nicolsflix.adapters.RecyclerSearchAdapter
import com.nicolas.nicolsflix.adapters.TrendingAdapter
import com.nicolas.nicolsflix.databinding.HomeFragmentBinding
import com.nicolas.nicolsflix.common.toLowerCase
import com.nicolas.nicolsflix.upcoming.utils.DataState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

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
        setRecyclerViewSearch()
        setRecyclerViewMovieTrending()
        setRecyclerViewMoviePopular()
        observeTextSearchListener()
        getMoviePopular()
    }

    private fun setRecyclerViewMoviePopular() {
        binding.moviePopularRecyclerView.run {
            viewModel.fetchPopularMovie()
            viewModel.listPopularMovie.observe(viewLifecycleOwner) {
                setHasFixedSize(true)
                adapter = TrendingAdapter(it) { onClickMovie ->
                    val directions =
                        HomeFragmentDirections.goToDetailsFragment(onClickMovie)
                    findNavController().navigate(directions)
                }
            }
        }
    }

    private fun getMoviePopular() {
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
                            HomeFragmentDirections.goToDetailsFragment(onClickMovie)
                        findNavController().navigate(directions)
                    }
                }
            }
        }
    }

    private fun setRecyclerViewMovieTrending() {
        binding.movieTrendingRecyclerView.run {
            viewModel.listTrendingMovie.observe(viewLifecycleOwner) {
                setHasFixedSize(true)
                adapter = TrendingAdapter(it) { onClickMovie ->
                    val directions =
                        HomeFragmentDirections.goToDetailsFragment(
                            onClickMovie
                        )
                    findNavController().navigate(directions)
                }
            }
        }
    }

    private fun observeTextSearchListener() {
        binding.include.editSearchMovieHome.addTextChangedListener { movieName ->
            if (movieName.isNullOrEmpty()) {
                binding.run {
                    setVisible(containerMovies = true)
                }

            } else {
                viewModel.fetchNameMovie(toLowerCase(movieName.toString()))
                binding.run {
                    setVisible(recyclerSearchMovies = true)
                }
            }
        }
    }

    private fun setVisible(
        containerMovies: Boolean = false,
        recyclerSearchMovies: Boolean = false,
        movieNotFound: Boolean = false
    ) {
        with(binding) {
            linearLayoutContainerMovies.isVisible = containerMovies
            movieSearchRecyclerView.isVisible = recyclerSearchMovies
            tvMovieNotFound.isVisible = movieNotFound
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}