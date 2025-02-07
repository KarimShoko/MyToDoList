package com.example.mytodolist.data

import com.example.mytodolist.domain.NoteItem

class NoteListMapper {

    fun mapEntityToDbModel(noteItem: NoteItem): NoteItemDbModel {
        return NoteItemDbModel(
            id = noteItem.id,
            name = noteItem.name,
            text = noteItem.text,
            priority = noteItem.priority
        )
    }

    fun mapDbModelToEntity(noteItemDbModel: NoteItemDbModel): NoteItem {
        return NoteItem(
            id = noteItemDbModel.id,
            name = noteItemDbModel.name,
            text = noteItemDbModel.text,
            priority = noteItemDbModel.priority
        )
    }

    fun mapListDbModelToListEntity(list: List<NoteItemDbModel>): List<NoteItem> {
        return list.map { mapDbModelToEntity(it) }
    }
}