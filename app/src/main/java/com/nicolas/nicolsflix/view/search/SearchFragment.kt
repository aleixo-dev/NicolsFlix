package com.nicolas.nicolsflix.view.search

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.adapters.RecyclerSearchAdapter
import com.nicolas.nicolsflix.common.toLowerCase
import com.nicolas.nicolsflix.databinding.SearchFragmentBinding
import com.nicolas.nicolsflix.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.search_fragment) {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var adapter: RecyclerSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            inputSearchMovie.addTextChangedListener { movieSearch ->
                viewModel.search(toLowerCase(movieSearch.toString()))
            }

            viewModel.listSearch.observe(viewLifecycleOwner) { listSearch ->

                if (listSearch != null) {
                    recyclerSearchMovie.layoutManager = GridLayoutManager(context, 3)
                    adapter = RecyclerSearchAdapter(listSearch) {
                        /*
                    val directions =
                        SearchFragmentDirections.actionSearchFragmentToDetailsFragment(it)
                    findNavController().navigate(directions)

                     */
                    }
                    recyclerSearchMovie.adapter = adapter
                    showSearch()
                } else {
                    hideSearch()
                }
            }
        }
    }


    private fun showSearch() = binding.apply {
        recyclerSearchMovie.visibility = View.VISIBLE
    }

    private fun hideSearch() = binding.apply {
        recyclerSearchMovie.visibility = View.GONE
    }
}