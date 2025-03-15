package com.example.mytodolist.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.mytodolist.data.database.AppDataBase
import com.example.mytodolist.data.mapper.NoteListMapper
import com.example.mytodolist.domain.NoteItem
import com.example.mytodolist.domain.NoteListRepository

class NoteListRepositoryImpl(application: Application) : NoteListRepository {

    private val noteListDao = AppDataBase.getInstance(application).noteListDao()//DAO
    private val mapper= NoteListMapper()
    


    override suspend fun addNoteItem(noteItem: NoteItem) {
        noteListDao.addNoteItem(mapper.mapEntityToDbModel(noteItem))
    }

    override suspend fun deleteNoteItem(noteItem: NoteItem) {
        noteListDao.deleteNoteItem(noteItem.id)
    }

    override suspend fun editNoteItem(noteItem: NoteItem) {
        noteListDao.addNoteItem(mapper.mapEntityToDbModel(noteItem))

    }

    override suspend fun getNoteItem(noteItemId: Int): NoteItem {
       val dbModel= noteListDao.getNoteItemId(noteItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getNoteList(): LiveData<List<NoteItem>> {
        return noteListDao.getNoteList().map {
            mapper.mapListDbModelToListEntity(it)
        }
    }
}