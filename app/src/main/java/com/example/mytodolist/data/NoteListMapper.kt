package com.example.mytodolist.data

import com.example.mytodolist.domain.NoteItem

class NoteListMapper {
//преобразует сущность domain-слоя в сущность data-слоя(DbModel)
    fun mapEntityToDbModel(noteItem: NoteItem):NoteItemDbModel{
        return NoteItemDbModel(
            id = noteItem.id,
            name = noteItem.name,
            text = noteItem.text,
            priority = noteItem.priority
        )
    }
    //и наоборот
    fun mapDbModelToEntity(noteItemDbModel: NoteItemDbModel):NoteItem{
        return NoteItem(
            id = noteItemDbModel.id,
            name = noteItemDbModel.name,
            text = noteItemDbModel.text,
            priority = noteItemDbModel.priority
        )
    }
    //преобразует лист объектов ShopIemDbModel в лист ShopItem
    //для каждого элемента данной коллекции (list: List<ShopItemDbModel>) вызовем mapDbModeltoEntity
    fun mapListDbModelToListEntity(list: List<NoteItemDbModel>): List<NoteItem> {
      return list.map { mapDbModelToEntity(it) }
    }
}