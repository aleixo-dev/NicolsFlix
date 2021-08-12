package com.nicolas.nicolsflix.view.mylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.adapters.MyListAdapter
import com.nicolas.nicolsflix.viewmodel.MyListViewModel
import kotlinx.android.synthetic.main.my_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyListFragment : Fragment(R.layout.my_list_fragment) {

    private val viewModel: MyListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMyListMovie()
        backMyListMovies.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getMyListMovie() {
        viewModel.myListMovie.observe(viewLifecycleOwner) { myListMovies ->
            with(movieListRecyclerView) {
                layoutManager = GridLayoutManager(context, 3)
                setHasFixedSize(true)
                adapter = MyListAdapter(myListMovies){
                    val directions = MyListFragmentDirections.fromListToGoDetailsFragment(it)
                    findNavController().navigate(directions)
                }
            }
        }
    }
}