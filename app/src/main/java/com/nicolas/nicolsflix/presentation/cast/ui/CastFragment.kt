package com.nicolas.nicolsflix.presentation.cast.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.common.Constants
import com.nicolas.nicolsflix.common.LoadImage
import com.nicolas.nicolsflix.common.loadImage
import com.nicolas.nicolsflix.databinding.CastFragmentBinding
import com.nicolas.nicolsflix.network.models.remote.CastDetail
import org.koin.androidx.viewmodel.ext.android.viewModel

class CastFragment : Fragment() {

    private var _binding: CastFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CastViewModel by viewModel()
    private val arguments: CastFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CastFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPersonDetail(arguments.personId)
        setupScreen()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupScreen() {
        viewModel.person.observe(viewLifecycleOwner) { person ->
            setupViews(person)
        }
    }

    private fun setupViews(person: CastDetail) {
        setupImage(person.profilePath)
        setupInfoPerson(person)
    }

    private fun setupImage(personImage: String) {
        binding.includeCast.imageViewCastPhotoDetail.apply {
            loadImage(
                this.context,
                "${Constants.LOAD_IMAGE_URL}$personImage"
            )
        }
    }

    private fun setupInfoPerson(person: CastDetail) {
        with(binding.includeCast) {
            textViewCastNameDetail.text = person.name
            textViewCastDepartmentName.text = person.knownForDepartment
            textViewCastBirthday.text = person.birthday
            textViewCastPlaceOfBirth.text = person.placeOfBirth
            textViewCastBiographyDetail.text = person.biography
        }
    }
}