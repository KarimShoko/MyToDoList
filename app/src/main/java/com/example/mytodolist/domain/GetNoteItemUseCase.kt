package com.example.mytodolist.domain

class GetNoteItemUseCase(private val noteListRepository: NoteListRepository) {
    suspend fun getNoteItem(noteItemId: Int): NoteItem {
        return noteListRepository.getNoteItem(noteItemId)
    }
}