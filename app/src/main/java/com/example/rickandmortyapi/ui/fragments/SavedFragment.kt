package com.example.rickandmortyapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.example.rickandmortyapi.databinding.FragmentSavedBinding
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.ui.MainActivity
import com.example.rickandmortyapi.ui.adapters.SavedRvAdapter
import com.example.rickandmortyapi.ui.stateholder.CharacterViewModel
import kotlinx.coroutines.*

class SavedFragment : Fragment(), SavedRvAdapter.ReceiveDataFromSavedFragment {
    lateinit var characterDb: CharacterDatabase
    lateinit var viewModel: CharacterViewModel
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!
    private lateinit var savedRvAdapter: SavedRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        characterDb = MainActivity().provideDataBase()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]
        viewModel.getCharacterFromDB(characterDb)

        observeViewModel()
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        // TODO: let me introduce my cringe-driven development
        savedRvAdapter = SavedRvAdapter(this)
        binding.rvCharacters.adapter = savedRvAdapter
        binding.rvCharacters.layoutManager = LinearLayoutManager(context)
    }

    private fun observeViewModel() {
        viewModel.characterRM.observe(viewLifecycleOwner) {
            if (it != null) savedRvAdapter.submitList(it)
        }
        viewModel.isDeleted.observe(viewLifecycleOwner) {
            if (it == true) {
                viewModel.resetDeletedValue()
            }
        }
    }

    override fun onReceiveDbInstance(): CharacterDatabase {
        return characterDb
    }

    override fun onReceiveViewModelInstance(): CharacterViewModel {
        return viewModel
    }
}