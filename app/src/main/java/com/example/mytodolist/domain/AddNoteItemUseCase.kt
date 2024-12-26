package com.example.mytodolist.domain
//Каждое действие бизнес логики,которое может вызвать пользователь,называется интрактором или юзкейсом.
class AddNoteItemUseCase(private val noteListRepository: NoteListRepository) {
    suspend fun addNoteItem(noteItem: NoteItem){
        noteListRepository.addNoteItem(noteItem)
    }
}