package com.example.mytodolist.domain

import androidx.lifecycle.LiveData

//Каждое действие бизнес логики,которое может вызвать пользователь,называется интрактором или юзкейсом.
class GetNoteListUseCase(private val noteListRepository: NoteListRepository){
    fun getNoteList(): LiveData<List<NoteItem>> {
       return noteListRepository.getNoteList()
    }
}