package com.example.rickandmortyapi.ui.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.databinding.ItemCardBinding
import com.example.rickandmortyapi.domain.enitity.CharacterRM

class SavedRvAdapter(var characters: List<CharacterRM>):
    RecyclerView.Adapter<SavedRvAdapter.SavedViewHolder>() {

    inner class SavedViewHolder(val binding: ItemCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(layoutInflater, parent, false)
        return SavedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        // TODO: kotlin extentions is deprecated, replace with binding
        holder.binding.apply {
            rvName.text = characters[position].name
            rvGender.text = characters[position].gender
            rvSpecies.text = characters[position].species
            rvOrigin.text = characters[position].origin
            rvImage.setImageDrawable(characters[position].image?.toDrawable(Resources.getSystem()))
            rvImage.setStrokeColorResource(
                when(characters[position].species) {
                    "Alive" -> R.color.green
                    "Dead" -> R.color.red
                    "Unknown" -> R.color.gray
                    else -> R.color.white
            })
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}