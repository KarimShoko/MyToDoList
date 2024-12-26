package com.example.mytodolist.domain
//Каждое действие бизнес логики,которое может вызвать пользователь,называется интрактором или юзкейсом.
class DeleteNoteItemUseCase(private val noteListRepository: NoteListRepository) {
    suspend fun deleteNoteItem(noteItem: NoteItem){
    noteListRepository.deleteNoteItem(noteItem)
    }
}