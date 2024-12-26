package com.example.mytodolist.domain

import androidx.lifecycle.LiveData

//должен уметь делать все,что требуется юзкейсам
interface NoteListRepository {
    suspend fun addNoteItem(noteItem: NoteItem)
    suspend fun deleteNoteItem(noteItem: NoteItem)
    suspend fun editNoteItem(noteItem: NoteItem)
    suspend fun getNoteItem(noteItemId: Int): NoteItem
    fun getNoteList(): LiveData<List<NoteItem>>
}