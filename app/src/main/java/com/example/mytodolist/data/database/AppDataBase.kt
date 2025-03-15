package com.example.mytodolist.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun noteListDao(): NoteListDao


    companion object {

        private var INSTANCE: AppDataBase? = null
        private val LOCK = Any()
        private val DB_NAME = "note_item.db"

        fun getInstance(application: Application): AppDataBase {
            INSTANCE?.let { return it }
            kotlin.synchronized(LOCK) {
                INSTANCE?.let { return it }
                val db = Room.databaseBuilder(
                    application,
                    AppDataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}