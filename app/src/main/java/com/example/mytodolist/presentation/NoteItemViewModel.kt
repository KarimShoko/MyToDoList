package com.example.mytodolist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mytodolist.data.repository.NoteListRepositoryImpl
import com.example.mytodolist.domain.AddNoteItemUseCase
import com.example.mytodolist.domain.EditNoteItemUseCase
import com.example.mytodolist.domain.GetNoteItemUseCase
import com.example.mytodolist.domain.NoteItem
import com.example.mytodolist.domain.Priority
import kotlinx.coroutines.launch

class NoteItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteListRepositoryImpl(application)

    private val addNoteItemUseCase = AddNoteItemUseCase(repository)
    private val editNoteItemUseCase = EditNoteItemUseCase(repository)
    private val getNoteItemUseCase = GetNoteItemUseCase(repository)

    private val _noteItem = MutableLiveData<NoteItem>()//
    val noteItem: LiveData<NoteItem>
        get() = _noteItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputText = MutableLiveData<Boolean>()
    val errorInputText: LiveData<Boolean>
        get() = _errorInputText

    fun getNoteItem(noteItemId: Int) {
        viewModelScope.launch {
            val item = getNoteItemUseCase.getNoteItem(noteItemId)
            _noteItem.value = item
        }
    }

    fun addNoteItem(inputName: String?, inputText: String?, priority: Priority) {
        val name = parseName(inputName)
        val text = parseText(inputText)
        val fieldsValid = validateInput(name, text)
        if (fieldsValid) {
            viewModelScope.launch {
                val noteItem = NoteItem(name = name, text = text, priority = priority)
                addNoteItemUseCase.addNoteItem(noteItem)
                _shouldCloseScreen.value = Unit
            }
        }
    }

    fun editNoteItem(inputName: String?, inputText: String?, priority: Priority) {
        val name = parseName(inputName)
        val text = parseText(inputText)
        val fieldsValid = validateInput(name, text)
        if (fieldsValid) {
            viewModelScope.launch {
                _noteItem.value?.let {
                    val item = it.copy(name = name, text = text, priority = priority)
                    editNoteItemUseCase.editNoteItem(item)
                    _shouldCloseScreen.value = Unit

                }
            }
        }
    }


    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }


    private fun parseText(inputText: String?): String {
        return inputText?.trim() ?: ""
    }


    private fun validateInput(name: String, text: String): Boolean {
        var result = true
        if (name.isBlank()) {
            result = false
            _errorInputName.value = true
        }
        if (text.isBlank()) {
            result = false
            _errorInputText.value = true
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputText() {
        _errorInputText.value = false
    }
}