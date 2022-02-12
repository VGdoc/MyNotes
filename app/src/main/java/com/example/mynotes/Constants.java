package com.example.mynotes;

public interface Constants {


    String REFRESH_NOTIFICATION = "refresh"; // уведомление от паблишера - обновить View
    String CURRENT_NOTE = "current_note"; // ключ для сохранения текущей заметки в onSavedInstanceState
    String MY_SIMPLE_ARRAY_LIST = "my_simple_array_list"; // ключ для сохранения и передачи списка заметок, экземпляра mySimpleArrayList
    SimpleNote DEFAULT_NOTE =
            new SimpleNote("Заметок ещё нет", "Заметки не выбраны"); //заметка по умолчанию для отображения на экране
}
