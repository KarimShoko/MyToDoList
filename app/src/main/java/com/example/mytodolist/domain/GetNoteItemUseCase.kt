package com.example.mytodolist.domain
//Каждое действие бизнес логики,которое может вызвать пользователь,называется интрактором или юзкейсом.
class GetNoteItemUseCase(private val noteListRepository: NoteListRepository) {
    fun getNoteItem(noteItemId:Int):NoteItem{
        return noteListRepository.getNoteItem(noteItemId)
    }
}