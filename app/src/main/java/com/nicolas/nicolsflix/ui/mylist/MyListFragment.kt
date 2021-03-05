package com.nicolas.nicolsflix.ui.mylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.data.db.NicolsDatabase
import com.nicolas.nicolsflix.data.repository.database.DatabaseDataSource
import com.nicolas.nicolsflix.ui.mylist.adapter.MyListAdapter
import kotlinx.android.synthetic.main.my_list_fragment.*

class MyListFragment : Fragment(R.layout.my_list_fragment) {

    private val viewModel: MyListViewModel by viewModels(
        factoryProducer = {
            val databaseDetails = NicolsDatabase.getInstance(requireContext())
            MyListViewModelFactory(databaseDataSource = DatabaseDataSource(databaseDetails.movieDao))
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMyListMovie()
        backMyListMovies.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getMyListMovie() {
        viewModel.getAllMyListMovie()
        viewModel.myListMovie.observe(viewLifecycleOwner) { myListMovies ->
            with(movieListRecyclerView) {
                layoutManager = GridLayoutManager(context, 3)
                setHasFixedSize(true)
                adapter = MyListAdapter(myListMovies){
                    val directions = MyListFragmentDirections.actionMyListFragmentToDetailsFragment(it)
                    findNavController().navigate(directions)
                }
            }
        }
    }
}