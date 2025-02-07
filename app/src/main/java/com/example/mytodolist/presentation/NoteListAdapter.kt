package com.example.mytodolist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mytodolist.databinding.NoteItemHighBinding
import com.example.mytodolist.databinding.NoteItemLowBinding
import com.example.mytodolist.databinding.NoteItemMediumBinding
import com.example.mytodolist.domain.NoteItem
import com.example.mytodolist.domain.Priority


class NoteListAdapter : ListAdapter<NoteItem, NoteItemViewHolder>(NoteItemDiffCallback()) {

    var onNoteItemClickListener: ((NoteItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            VIEW_TYPE_LOW -> NoteItemLowBinding.inflate(layoutInflater, parent, false)
            VIEW_TYPE_MEDIUM -> NoteItemMediumBinding.inflate(layoutInflater, parent, false)
            VIEW_TYPE_HIGH -> NoteItemHighBinding.inflate(layoutInflater, parent, false)
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        return NoteItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val noteItem = getItem(position)

        holder.binding.root.setOnClickListener {
            onNoteItemClickListener?.invoke(noteItem)
        }

        when (val binding = holder.binding) {
            is NoteItemLowBinding -> {
                binding.tvName.text = noteItem.name
                binding.tvText.text = noteItem.text
            }

            is NoteItemMediumBinding -> {
                binding.tvName.text = noteItem.name
                binding.tvText.text = noteItem.text
            }

            is NoteItemHighBinding -> {
                binding.tvName.text = noteItem.name
                binding.tvText.text = noteItem.text
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val noteItem = getItem(position)
        return when (noteItem.priority) {
            Priority.LOW -> VIEW_TYPE_LOW
            Priority.MEDIUM -> VIEW_TYPE_MEDIUM
            Priority.HIGH -> VIEW_TYPE_HIGH
        }
    }

    companion object {
        const val VIEW_TYPE_LOW = 100
        const val VIEW_TYPE_MEDIUM = 101
        const val VIEW_TYPE_HIGH = 102
        const val MAX_POOL_SIZE = 15
    }
}