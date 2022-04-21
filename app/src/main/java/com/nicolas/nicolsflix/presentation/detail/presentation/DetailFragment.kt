package com.nicolas.nicolsflix.presentation.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.common.showToast
import com.nicolas.nicolsflix.databinding.DetailFragmentBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val arguments: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTopAppBar()
        setupMovie()
        showToast(arguments.movie.toString())
    }

    private fun setupTopAppBar() {
        binding.topAppBar.apply {
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.favorite_icon -> true
                    R.id.share_icon -> true
                    else -> false
                }
            }

            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setupMovie() {
        binding.apply {
            Picasso.get().load(
                "https://image.tmdb.org/t/p/w500/${arguments.movie.posterDetails}"
            ).into(imageView5)
            textViewNameMovie.text = arguments.movie.title
            textViewDescriptionMovie.text = arguments.movie.description
            textViewRateMovie.text = arguments.movie.rating
        }
    }
}