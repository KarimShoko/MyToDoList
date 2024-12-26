package com.example.mytodolist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.mytodolist.data.NoteListRepositoryImpl
import com.example.mytodolist.domain.AddNoteItemUseCase
import com.example.mytodolist.domain.DeleteNoteItemUseCase
import com.example.mytodolist.domain.GetNoteListUseCase
import com.example.mytodolist.domain.NoteListRepository

class NoteListViewModel(private val application: Application):AndroidViewModel(application) {

    val repository=NoteListRepositoryImpl(application)

    private val getNoteListUseCase=GetNoteListUseCase(repository)
    private val deleteNoteItemUseCase=DeleteNoteItemUseCase(repository)

    val noteList=getNoteListUseCase.getNoteList()
}