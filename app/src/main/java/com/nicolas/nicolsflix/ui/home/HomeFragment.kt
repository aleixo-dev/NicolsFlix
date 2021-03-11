package com.nicolas.nicolsflix.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.ui.home.adapter.TrendingAdapter
import com.nicolas.nicolsflix.ui.search.adapter.RecyclerSearchAdapter
import com.nicolas.nicolsflix.utils.toLowerCase
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: RecyclerSearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMyButtonList()
        initRecyclerViewSearchMovie()
        initRecyclerViewTrendingMovie()

        editSearchMovie.addTextChangedListener { searchMovie ->
            viewModel.callSearchMovie(toLowerCase(searchMovie.toString()))
        }
        movieTrendingRecyclerView.visibility = View.VISIBLE

    }

    private fun initRecyclerViewSearchMovie(){

        viewModel.listSearchMovie.observe(viewLifecycleOwner) { listSearch ->

            movieTrendingRecyclerView.visibility = View.VISIBLE

            if (listSearch != null) {

                movieTrendingRecyclerView.visibility = View.GONE
                recyclerViewSearch.visibility = View.VISIBLE
                recyclerViewSearch.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                adapter = RecyclerSearchAdapter(listSearch) {

                    val directions = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                    findNavController().navigate(directions)
                }
                recyclerViewSearch.adapter = adapter
            } else {
                recyclerViewSearch.visibility = View.GONE
            }
        }
    }

    private fun initRecyclerViewTrendingMovie() {

        viewModel.listMovieTrending.observe(viewLifecycleOwner) { listMovieTrending ->
            with(movieTrendingRecyclerView) {
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = TrendingAdapter(listMovieTrending) { clickMovie ->
                    val directions =
                        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(clickMovie)
                    findNavController().navigate(directions)
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