package com.example.rickandmortyapi.ui.fragments

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.example.rickandmortyapi.databinding.FragmentCharacterDbBinding
import com.example.rickandmortyapi.ui.MainActivity
import com.example.rickandmortyapi.ui.stateholder.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDbFragment : Fragment() {

    private val viewModel by viewModels<CharacterViewModel>()
    lateinit var characterDb: CharacterDatabase
    private var _binding: FragmentCharacterDbBinding? = null
    private val binding get() = _binding!!
    private val args: CharacterDbFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDbBinding.inflate(inflater, container, false)
        characterDb = MainActivity.characterDb
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacterFromDbById(characterDb, args.idDb)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.characterRMById.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.charGenderDb.text = it.gender
                binding.charLocationDb.text = it.location
                binding.charNameDb.text = it.name
                binding.charOriginDb.text = it.origin
                binding.charSpeciesDb.text = it.species
                binding.charStatusDb.text = it.status
                binding.charImageDb.setImageDrawable(it.image?.toDrawable(Resources.getSystem()))
                stateSelector()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun stateSelector() {
        when(binding.charStatusDb.text) {
            "Alive" -> binding.charImageDb.setStrokeColorResource(R.color.green)
            "Dead" -> binding.charImageDb.setStrokeColorResource(R.color.red)
            "unknown" -> binding.charImageDb.setStrokeColorResource(R.color.gray)
            else -> binding.charImageDb.setStrokeColorResource(R.color.white)
        }
    }

}