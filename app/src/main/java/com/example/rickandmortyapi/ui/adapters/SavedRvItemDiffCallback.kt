package com.example.rickandmortyapi.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortyapi.domain.enitity.CharacterRM

class SavedRvItemDiffCallback : DiffUtil.ItemCallback<CharacterRM>(){
    override fun areItemsTheSame(oldItem: CharacterRM, newItem: CharacterRM): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterRM, newItem: CharacterRM): Boolean {
        return oldItem == newItem
    }
}