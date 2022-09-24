package com.example.rickandmortyapi.ui.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.example.rickandmortyapi.databinding.ItemCardBinding
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.ui.fragments.SavedFragmentDirections
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyapi.ui.MainActivity
import com.example.rickandmortyapi.ui.fragments.SearchFragmentDirections
import com.example.rickandmortyapi.ui.stateholder.CharacterViewModel

class SavedRvAdapter(listener: ReceiveDataFromSavedFragment)
    : ListAdapter<CharacterRM, SavedRvAdapter.SavedViewHolder>(SavedRvItemDiffCallback()) {

    val characterDb = MainActivity().provideDataBase()
    val viewModel = listener.onReceiveViewModelInstance()

    inner class SavedViewHolder(private val binding: ItemCardBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(savedItem: CharacterRM) {
            binding.apply {
                rvName.text = savedItem.name
                rvGender.text = savedItem.gender
                rvSpecies.text = savedItem.species
                rvOrigin.text = savedItem.origin
                rvImage.setImageDrawable(savedItem.image?.toDrawable(Resources.getSystem()))
                rvImage.setStrokeColorResource(
                    when(savedItem.species) {
                        "Alive" -> R.color.green
                        "Dead" -> R.color.red
                        "Unknown" -> R.color.gray
                        else -> R.color.white
                    })
                btnDelete.setOnClickListener {
                    viewModel.deleteCharacterFromDB(characterDb, savedItem)
                }
                btnDetails.setOnClickListener {
                    val action = savedItem.id?.let { it1 ->
                        SavedFragmentDirections.actionSavedFragmentToCharacterDbFragment(it1)
                    }
                    if (action != null) {
                        it.findNavController().navigate(action)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        val binding = ItemCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SavedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        val savedItem = getItem(position)
        holder.bind(savedItem)
    }

    interface ReceiveDataFromSavedFragment {
        fun onReceiveDbInstance(): CharacterDatabase
        fun onReceiveViewModelInstance(): CharacterViewModel
    }
}