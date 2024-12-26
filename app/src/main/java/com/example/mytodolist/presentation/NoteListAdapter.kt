package com.example.mytodolist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodolist.R
import com.example.mytodolist.domain.NoteItem
import com.example.mytodolist.domain.Priority

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    //коллекция элементов,которую будем отображать
    var noteList = listOf<NoteItem>()
        //чтобы устанавливать значения снаружи
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_LOW -> R.layout.note_item_low
            VIEW_TYPE_MEDIUM -> R.layout.note_item_medium
            VIEW_TYPE_HIGH -> R.layout.note_item_hight
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val noteItem = noteList[position]
        holder.tvName.text = noteItem.name
        holder.tvText.text = noteItem.text

    }

    override fun getItemCount(): Int {//возвращает кол-во элементов коллекции
        return noteList.size
    }

    override fun getItemViewType(position: Int): Int {
        val noteItem = noteList[position]
        return when (noteItem.priority) {
            Priority.LOW -> VIEW_TYPE_LOW
            Priority.MEDIUM -> VIEW_TYPE_MEDIUM
            Priority.HIGH -> VIEW_TYPE_HIGH
        }
    }

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvText = view.findViewById<TextView>(R.id.tv_text)
    }

    interface onNoteItemClickListener{//интерфейс на долгое нажатие
        fun onShopItemLongClickListener(noteItem: NoteItem)
    }

    companion object {
        const val VIEW_TYPE_LOW = 100
        const val VIEW_TYPE_MEDIUM = 101
        const val VIEW_TYPE_HIGH = 102
        const val MAX_POOL_SIZE = 15


    }
}