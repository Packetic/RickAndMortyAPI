package com.example.rickandmortyapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapi.data.local.room.CharacterDatabase
import com.example.rickandmortyapi.databinding.FragmentSavedBinding
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.ui.adapters.SavedRvAdapter
import kotlinx.coroutines.*

class SavedFragment : Fragment() {
    lateinit var characterDb: CharacterDatabase
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!
    private val job = Job()
    private val savedFragmentScope = CoroutineScope(Dispatchers.Main + job)
    lateinit var list: List<CharacterRM>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        readData()
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        characterDb = CharacterDatabase.getDatabase(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SavedRvAdapter(list)
        binding.rvCharacters.adapter = adapter
        binding.rvCharacters.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // TODO: move to viewmodel
    private fun readData() {
        lateinit var characterRMList: List<CharacterRM>
        savedFragmentScope.launch {
            // TODO: getAll() method is not working
            characterRMList = characterDb.characterDao().getAll()
            list = characterRMList
        }
    }
}