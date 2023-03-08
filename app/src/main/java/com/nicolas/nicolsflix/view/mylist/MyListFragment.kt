package com.nicolas.nicolsflix.view.mylist

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.adapters.MyListAdapter
import com.nicolas.nicolsflix.databinding.MyListFragmentBinding
import com.nicolas.nicolsflix.viewmodel.MyListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyListFragment : Fragment(R.layout.my_list_fragment) {

    private var _binding: MyListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMyListMovie()
        binding.apply {
            backMyListMovies.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun getMyListMovie() = binding.apply {
        viewModel.myListMovie.observe(viewLifecycleOwner) { myListMovies ->
            with(movieListRecyclerView) {
                layoutManager = GridLayoutManager(context, 3)
                setHasFixedSize(true)
                adapter = MyListAdapter(myListMovies) {
                    val directions = MyListFragmentDirections.fromListToGoDetailsFragment(it)
                    findNavController().navigate(directions)
                }
            }
        }
    }
}