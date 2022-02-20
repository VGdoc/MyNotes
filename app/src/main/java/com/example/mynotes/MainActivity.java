package com.example.mynotes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    public static NotesMainContainer notesList = new NotesMainContainer(); // основной список заметок
    private static int testNoteCounter = 0; // счётчик для тестовой кнопки, участвует в динамическом присвоении имён заметок

    private static boolean arrayFillOnlyOnceFlag = true; // флаг, чтобы добавлять замекти только 1 раз при запуске приложения, нужен для тестов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Для тестов добавляются несколько заметок перед запуском
        addFewNotesForTests();

        if (savedInstanceState == null) {
            // Основной фрагмент на экране
            FragmentNoteTitles fragmentNoteTitles = FragmentNoteTitles.newInstance(notesList);
            getSupportFragmentManager().beginTransaction().add(R.id.titles, fragmentNoteTitles).commit();

            // фрагмент с кнопками навигации
            FragmentButtonsNavigationMenu fragmentButtonsNavigationMenu = FragmentButtonsNavigationMenu.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.navigation_block, fragmentButtonsNavigationMenu).commit();
        }

        // toolbar
        setSupportActionBar(findViewById(R.id.toolbar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_about):
                new DialogAboutFragment().show(getSupportFragmentManager(),"");
                break;
            case (R.id.action_settings):
                getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fullscreen, FragmentSettings.newInstance()).addToBackStack("").commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addFewNotesForTests() {
        if (arrayFillOnlyOnceFlag) {
            for (int i = 0; i < 5; i++) {
                notesList.addNewNote("test note " + testNoteCounter, "test content " + testNoteCounter);
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

    /**
     * Пришлось перенести наш костыль в onResume
     * так как не onBackPressed() вызывать в onCreate - черевато
     **/
    @Override
    protected void onResume() {
        super.onResume();
        // ищем фрагмент, который сидит в контейнере R.id.cities_container
        Fragment backStackFragment = (Fragment) getSupportFragmentManager()
                .findFragmentById(R.id.titles);
        // если такой есть, и он является CoatOfArmsFragment
        if (backStackFragment != null && backStackFragment instanceof FragmentNoteContent) {
            //то сэмулируем нажатие кнопки Назад
            onBackPressed();
        }
    }

}