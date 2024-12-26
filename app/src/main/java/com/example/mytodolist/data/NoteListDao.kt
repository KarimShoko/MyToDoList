package com.example.mytodolist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteListDao {
    //возвращает список всех записей
    @Query("SELECT * FROM note_items")//ВЫБРАТЬ ВСЕ ИЗ ТАБЛИЦЫ NOTE_ITEMS
    fun getNoteList(): LiveData<List<NoteItemDbModel>>
    //т.к возвращаемый тип LD-Room сам переключит поток

    //добавление.onConflict-если в бд будет добавлен объект с тем же ИД-он перезапишется
    //Если такого ИД в базе нет-добавлен будет новый
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNoteItem(nteItemDbModel: NoteItemDbModel)

    //удаление заметки
    @Query("DELETE FROM note_items WHERE id=:noteItemId ")
    suspend fun deleteNoteItem(noteItemId: Int)

    //возвращает 1 элемент
    @Query("SELECT * FROM note_items WHERE id=:noteItemId LIMIT 1 ")
    suspend fun getNoteItemId(noteItemId: Int): NoteItemDbModel
}
//Suspend-чтобы методы не блокировали поток,а могли прервать свое выполнение на время,пока операция не будет завершена