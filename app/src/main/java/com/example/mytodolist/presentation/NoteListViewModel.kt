package com.example.mytodolist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodolist.data.repository.NoteListRepositoryImpl
import com.example.mytodolist.domain.DeleteNoteItemUseCase
import com.example.mytodolist.domain.GetNoteListUseCase
import com.example.mytodolist.domain.NoteItem
import kotlinx.coroutines.launch

class NoteListViewModel(private val application: Application) : AndroidViewModel(application) {

    private val repository = NoteListRepositoryImpl(application)

    private val getNoteListUseCase = GetNoteListUseCase(repository)
    private val deleteNoteItemUseCase = DeleteNoteItemUseCase(repository)

    val noteList = getNoteListUseCase.getNoteList()

    fun deleteNoteItem(noteItem: NoteItem) {
        viewModelScope.launch { deleteNoteItemUseCase.deleteNoteItem(noteItem) }

    }

}