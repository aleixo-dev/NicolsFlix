package com.nicolas.nicolsflix.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.adapters.RecyclerSearchAdapter
import com.nicolas.nicolsflix.adapters.TrendingAdapter
import com.nicolas.nicolsflix.databinding.HomeFragmentBinding
import com.nicolas.nicolsflix.home.utils.DataState
import com.nicolas.nicolsflix.utils.showToast
import com.nicolas.nicolsflix.utils.toLowerCase
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.home_fragment) {

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setRecyclerViewMoviePopular() {
        binding.moviePopularRecyclerView.run {
            viewModel.fetchPopularMovie()
            viewModel.listPopularMovie.observe(viewLifecycleOwner, {
                setHasFixedSize(true)
                adapter = TrendingAdapter(it) { onClickMovie ->
                    val directions =
                        HomeFragmentDirections.goToDetailsFragment(onClickMovie)
                    findNavController().navigate(directions)
                }
            })
        }
    }

    private fun getMoviePopular() {
        viewModel.run {
            getMoviePopular()
            moviePopularList.observe(viewLifecycleOwner) { movie ->
                when (movie) {
                    is DataState.Loading -> {
                        // TODO
                    }
                    is DataState.Success -> {
                        showToast(movie.result[0].title)
                    }
                    is DataState.Error -> {
                        // TODO
                    }
                    is DataState.Empty -> {
                        // TODO
                    }
                }
            }
        }
    }

    private fun setRecyclerViewSearch() {
        binding.movieSearchRecyclerView.run {
            viewModel.listNamesMovie.observe(viewLifecycleOwner, {
                setHasFixedSize(true)
                if (it.isEmpty()) {
                    binding.tvMovieNotFound.visibility = View.VISIBLE
                    binding.movieSearchRecyclerView.visibility = View.GONE
                } else {
                    binding.tvMovieNotFound.visibility = View.GONE
                    adapter = RecyclerSearchAdapter(it) { onClickMovie ->
                        val directions =
                            HomeFragmentDirections.goToDetailsFragment(onClickMovie)
                        findNavController().navigate(directions)
                    }
                }
            })
        }
    }

    private fun setRecyclerViewMovieTrending() {
        binding.movieTrendingRecyclerView.run {
            viewModel.listTrendingMovie.observe(viewLifecycleOwner, {
                setHasFixedSize(true)
                adapter = TrendingAdapter(it) { onClickMovie ->
                    val directions =
                        HomeFragmentDirections.goToDetailsFragment(
                            onClickMovie
                        )
                    findNavController().navigate(directions)
                }
            })
        }
    }

    private fun observeTextSearchListener() {
        binding.include.editSearchMovieHome.addTextChangedListener { movieName ->
            if (movieName.isNullOrEmpty()) {
                binding.run {
                    moviePopularRecyclerView.visibility = View.VISIBLE
                    movieTrendingRecyclerView.visibility = View.VISIBLE
                    movieSearchRecyclerView.visibility = View.GONE
                    tvTrendingName.visibility = View.VISIBLE
                    tvPopularName.visibility = View.VISIBLE
                }

            } else {
                viewModel.fetchNameMovie(toLowerCase(movieName.toString()))
                binding.run {
                    moviePopularRecyclerView.visibility = View.GONE
                    movieTrendingRecyclerView.visibility = View.GONE
                    movieSearchRecyclerView.visibility = View.VISIBLE
                    tvTrendingName.visibility = View.GONE
                    tvPopularName.visibility = View.GONE
                }
            }
        }
    }
}