package com.example.mytodolist.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.mytodolist.domain.NoteItem

class NoteItemDiffCallback : DiffUtil.ItemCallback<NoteItem>() {

    override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
        return oldItem == newItem
    }
}