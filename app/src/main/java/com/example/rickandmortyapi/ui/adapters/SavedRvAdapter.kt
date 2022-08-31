package com.example.rickandmortyapi.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.ui.rvmodel.CharacterRv
import kotlinx.android.synthetic.main.item_card.view.*

class SavedRvAdapter(var characters: List<CharacterRv>):
    RecyclerView.Adapter<SavedRvAdapter.SavedViewHolder>() {

    inner class SavedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return SavedViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        // TODO: kotlin extentions is deprecated, replace with binding
        holder.itemView.apply {
            rvName.text = characters[position].name
            rvGender.text = characters[position].gender
            rvSpecies.text = characters[position].species
            rvOrigin.text = characters[position].origin
            rvImage.setImageResource(characters[position].image)
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}