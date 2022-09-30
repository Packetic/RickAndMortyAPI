package com.example.rickandmortyapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapi.databinding.FragmentSavedBinding
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.ui.adapters.SavedRvAdapter
import com.example.rickandmortyapi.ui.stateholder.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment() {
    private val viewModel by viewModels<CharacterViewModel>()
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!
    private lateinit var savedRvAdapter: SavedRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacterFromDB()

        observeViewModel()
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        savedRvAdapter = SavedRvAdapter(::deleteCharacter)
        binding.rvCharacters.adapter = savedRvAdapter
        binding.rvCharacters.layoutManager = LinearLayoutManager(context)
    }

    private fun observeViewModel() {
        viewModel.characterRM.observe(viewLifecycleOwner) {
            if (it != null) savedRvAdapter.submitList(it)
        }
    }

    private fun deleteCharacter(characterRM: CharacterRM) {
        viewModel.deleteCharacterFromDB(characterRM)
    }
}