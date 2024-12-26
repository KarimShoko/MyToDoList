package com.example.mytodolist.domain

import android.webkit.WebSettings.RenderPriority
//data class-предназачен для хранения данных.Автоматически создаёт стандартные методы:
//toString(): для удобного представления данных в виде строки.
//equals() и hashCode(): для сравнения объектов.
//copy(): для создания копии объекта с возможностью изменения отдельных свойств.
data class NoteItem(
    val id: Int,
    val name:String,
    val text:String,
    val priority: Priority
    ) {
}