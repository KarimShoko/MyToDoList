package com.example.mytodolist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mytodolist.domain.Priority
@Entity(tableName = "note_items")
data class NoteItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name:String,
    val text:String,
    val priority: Priority
)
