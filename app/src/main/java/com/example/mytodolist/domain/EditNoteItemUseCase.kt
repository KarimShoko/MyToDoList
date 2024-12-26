package com.example.mytodolist.domain
//Каждое действие бизнес логики,которое может вызвать пользователь,называется интрактором или юзкейсом.
class EditNoteItemUseCase(private val noteListRepository: NoteListRepository) {
    suspend fun editNoteItem(noteItem: NoteItem){
        noteListRepository.editNoteItem(noteItem)
    }
}