package com.example.rickandmortyapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.databinding.FragmentCharacterBinding
import com.example.rickandmortyapi.ui.stateholder.CharacterViewModel

class CharacterFragment : Fragment() {
    lateinit var viewModel: CharacterViewModel
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!
    private val args: CharacterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]
        viewModel.loadCharacterData(args.id)
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.character.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.charGender.text = it.body()?.gender
                binding.charLocation.text = it.body()?.location?.name
                binding.charName.text = it.body()?.name
                binding.charOrigin.text = it.body()?.origin?.name
                binding.charSpecies.text = it.body()?.species
                binding.charStatus.text = it.body()?.status
                loadImage(it.body()?.image)
                stateSelector()
            }
        }
    }

    private fun loadImage(image: String?) {
        Glide.with(requireContext())
            .load(image)
            .fitCenter()
            .into(binding.charImage)
    }

    private fun stateSelector() {
        when(binding.charStatus.text) {
            "Alive" -> binding.charImage.setStrokeColorResource(R.color.green)
            "Dead" -> binding.charImage.setStrokeColorResource(R.color.red)
            "Unknown" -> binding.charImage.setStrokeColorResource(R.color.gray)
            else -> binding.charImage.setStrokeColorResource(R.color.white)
        }
    }
}