package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements PublisherGetter{

    public static MySimpleNotesArrayList testNotes = new MySimpleNotesArrayList();
    Button addNoteButton;
    private static int testNoteCounter = 0;
    private static final String MY_SIMPLE_ARRAY_LIST = "my_simple_array_list";
    // Создаём класс Паблишера
    private Publisher publisher = new Publisher();
    public static final String REFRESH_NOTIFICATION = "refresh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // кнопка добавления новой заметки в тестововм режиме
        addNoteButton = findViewById(R.id.btn_add_new_note);
        addNoteButton.setText("Добавить новую заметку (тест)");
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleNote testNewNote = new SimpleNote("test note " + testNoteCounter, "test content " + testNoteCounter);
                testNotes.addNewNote(testNewNote);
                testNoteCounter++;
                publisher.notify(REFRESH_NOTIFICATION);
            }
        });


        NoteTitles noteTitles = NoteTitles.newInstance(testNotes);
        getSupportFragmentManager().beginTransaction().add(R.id.titles, noteTitles).commit();
        publisher.subscribe(noteTitles);


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MY_SIMPLE_ARRAY_LIST, testNotes);
    }

    /**
     * Нагло скопировано и подстроено
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
        if (backStackFragment != null && backStackFragment instanceof NoteContent) {
            //то сэмулируем нажатие кнопки Назад
            onBackPressed();
        }
    }


    @Override
    public Publisher getPublisher() {
        return null;
    }

}