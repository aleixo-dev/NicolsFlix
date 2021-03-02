package com.nicolas.nicolsflix.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.ui.home.adapter.PopularAdapter
import com.nicolas.nicolsflix.ui.home.adapter.RecommendAdapter
import com.nicolas.nicolsflix.ui.home.adapter.TrendingAdapter
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMyButtonList()
        initRecyclerViewPopularMovie()
        initRecyclerViewTrendingMovie()
        initRecyclerViewRecommendMovie()

    }

    private fun initRecyclerViewPopularMovie() {
        viewModel.callMoviePopular()
        viewModel.listMoviePopular.observe(viewLifecycleOwner, { listMovies ->
            with(moviePopularRecyclerView) {

                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = PopularAdapter(listMovies) { movie ->

                    val directions =
                        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie)

                    findNavController().navigate(directions)
                }
            }
        })
    }


    private fun initRecyclerViewTrendingMovie() {

        viewModel.callMovieTrending()
        viewModel.listMovieTrending.observe(viewLifecycleOwner) { listMovieTrending ->
            with(movieTrendingRecyclerView) {
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = TrendingAdapter(listMovieTrending) {
                    val directions = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                    findNavController().navigate(directions)
                }
            }
        }
    }

    private fun initRecyclerViewRecommendMovie() {

        viewModel.callMovieRecommend()
        viewModel.listMovieRecommend.observe(viewLifecycleOwner) { listMovieRecommend ->
            with(movieRecommendRecyclerView) {
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = RecommendAdapter(listMovieRecommend) {
                    val directionsRecommend =
                        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                    findNavController().navigate(directionsRecommend)
                }
            }
        }
    }

    private fun initMyButtonList() {
        buttonMyList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_myListFragment)
        }
    }
}