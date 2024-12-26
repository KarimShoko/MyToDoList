package com.example.mytodolist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodolist.R

class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvName=view.findViewById<TextView>(R.id.tv_name)
    val tvText=view.findViewById<TextView>(R.id.tv_text)
}