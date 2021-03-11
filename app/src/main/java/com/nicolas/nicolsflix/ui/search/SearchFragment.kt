package com.nicolas.nicolsflix.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.ui.search.adapter.RecyclerSearchAdapter
import com.nicolas.nicolsflix.utils.toLowerCase
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.search_fragment) {

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var adapter: RecyclerSearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        input_search_movie.addTextChangedListener { movieSearch ->
            viewModel.search(toLowerCase(movieSearch.toString()))
        }

        viewModel.listSearch.observe(viewLifecycleOwner) { listSearch ->

            if (listSearch != null) {
                recyclerSearchMovie.layoutManager = GridLayoutManager(context,3)
                adapter = RecyclerSearchAdapter(listSearch) {
                    val directions =
                        SearchFragmentDirections.actionSearchFragmentToDetailsFragment(it)
                    findNavController().navigate(directions)
                }
                recyclerSearchMovie.adapter = adapter
                showSearch()
            }else{
                hideSearch()
            }
        }
    }


    private fun showSearch() {
        recyclerSearchMovie.visibility = View.VISIBLE
    }

    private fun hideSearch() {
        recyclerSearchMovie.visibility = View.GONE
    }
}