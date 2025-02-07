package com.example.mytodolist.domain

data class NoteItem(
    val id: Int = UNDEFINED_ID,
    val name: String,
    val text: String,
    val priority: Priority
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}