package com.example.mytodolist.domain

class DeleteNoteItemUseCase(private val noteListRepository: NoteListRepository) {
    suspend fun deleteNoteItem(noteItem: NoteItem) {
        noteListRepository.deleteNoteItem(noteItem)
    }
}