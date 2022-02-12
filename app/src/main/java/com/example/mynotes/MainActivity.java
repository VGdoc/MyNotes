package com.example.mynotes;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static MySimpleNotesArrayList notesList = new MySimpleNotesArrayList(); // основной список заметок
    Button addNoteButton; // тестовая кнопка добавления новой заметки
    private static int testNoteCounter = 0; // счётчик для тестовой кнопки, участвует в динамическом присвоении имён заметок

    private static boolean arrayFillOnlyOnceFlag = true; // флаг, чтобы добавлять замекти только 1 раз при запуске приложения, нужен для тестов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Для тестов добавляются несколько заметок перед запуском
        addFewNotesForTests();

        // Основной фрагмент на экране
        FragmentNoteTitles fragmentNoteTitles = FragmentNoteTitles.newInstance(notesList);
        getSupportFragmentManager().beginTransaction().add(R.id.titles, fragmentNoteTitles).commit();

        // фрагмент с кнопками навигации
        FragmentButtonsNavigationMenu fragmentButtonsNavigationMenu = FragmentButtonsNavigationMenu.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.navigation_block, fragmentButtonsNavigationMenu).commit();

    }

    private void addFewNotesForTests() {
        if (arrayFillOnlyOnceFlag) {
            for (int i = 0; i < 5; i++) {
                SimpleNote testNewNote = new SimpleNote("test note " + testNoteCounter, "test content " + testNoteCounter);
                notesList.addNewNote(testNewNote);
                testNoteCounter++;
            }
            arrayFillOnlyOnceFlag = false;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.MY_SIMPLE_ARRAY_LIST, notesList);
    }

}