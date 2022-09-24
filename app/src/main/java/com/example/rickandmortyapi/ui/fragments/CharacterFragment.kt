package com.example.rickandmortyapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.example.rickandmortyapi.databinding.FragmentCharacterBinding
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.ui.MainActivity
import com.example.rickandmortyapi.ui.ProvideDataBase
import com.example.rickandmortyapi.ui.stateholder.CharacterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CharacterFragment : Fragment() {

    lateinit var viewModel: CharacterViewModel
    lateinit var characterDb: CharacterDatabase
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
        // TODO: boilerplate
        characterDb = MainActivity().provideDataBase()
        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]
        viewModel.loadCharacterData(args.id)
        observeViewModel()

        binding.btnSave.setOnClickListener {
            saveData()
            Toast.makeText(context,
                "${binding.charName.text} is in your collection now!",
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.characterResponse.observe(viewLifecycleOwner) {
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

    // TODO: move to viewmodel
    private fun saveData() {
        val characterRM = setupCharacterRM()
        viewModel.writeCharacterToDB(characterDb, characterRM)
    }

    private fun setupCharacterRM(): CharacterRM {
        val gender = binding.charGender.text.toString()
        val location = binding.location.text.toString()
        val name = binding.charName.text.toString()
        val origin = binding.charOrigin.text.toString()
        val species = binding.charSpecies.text.toString()
        val status = binding.charStatus.text.toString()
        val image = binding.charImage.drawable.toBitmap()

        return CharacterRM(null, name, status, gender, location, species, origin, image)
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
            "unknown" -> binding.charImage.setStrokeColorResource(R.color.gray)
            else -> binding.charImage.setStrokeColorResource(R.color.white)
        }
    }
}