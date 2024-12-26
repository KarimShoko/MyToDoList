package com.example.mytodolist.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun noteListDao(): NoteListDao//возвращает экземпляр интерфейса DAO(для работы с БД)

    //Singleton
    companion object {

        private var INSTANCE: AppDataBase? = null//хранит экземпляр БД
        private val LOCK = Any()
        private val DB_NAME = "note_item.db"

        //чтобы получить БД-нужен контекст
        fun getInstance(application: Application): AppDataBase {//возвращает БД
            INSTANCE?.let { return it }//если переменной уже присвоено значение-то сразу вернем
            kotlin.synchronized(LOCK) {
                INSTANCE?.let { return it }
                val db = Room.databaseBuilder(//создаем экземпляр БД
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