package com.example.rickandmortyapi.ui.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.databinding.ItemCardBinding
import com.example.rickandmortyapi.domain.enitity.CharacterRM

class SavedRvAdapter : ListAdapter<CharacterRM, SavedRvAdapter.SavedViewHolder>(SavedRvItemDiffCallback()) {

    inner class SavedViewHolder(val binding: ItemCardBinding): RecyclerView.ViewHolder(binding.root) {
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
}