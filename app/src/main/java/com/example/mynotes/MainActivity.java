package com.example.mynotes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements PublisherGetter {

    public static MySimpleNotesArrayList notesList = new MySimpleNotesArrayList(); // основной список заметок
    Button addNoteButton; // тестовая кнопка добавления новой заметки
    private static int testNoteCounter = 0; // счётчик для тестовой кнопки, участвует в динамическом присвоении имён заметок

    // Создаём класс Паблишера
    private final Publisher publisher = new Publisher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // кнопка добавления новой заметки в тестовом режиме
        addNoteButton = findViewById(R.id.btn_add_new_note);
        addNoteButton.setText("Добавить новую заметку (тест)");
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleNote testNewNote = new SimpleNote("test note " + testNoteCounter, "test content " + testNoteCounter);
                notesList.addNewNote(testNewNote);
                testNoteCounter++;
                publisher.notify(Constants.REFRESH_NOTIFICATION);
            }
        });


        FragmentNoteTitles fragmentNoteTitles = FragmentNoteTitles.newInstance(notesList);
        getSupportFragmentManager().beginTransaction().add(R.id.titles, fragmentNoteTitles).commit();
        publisher.subscribe(fragmentNoteTitles); // подписываем фрагмент с заголовками на нажатия кнопки


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.MY_SIMPLE_ARRAY_LIST, notesList);
    }


    /**
     * Метод из методички, особо не разбирался, вроде бы для того,
     * чтобы фрагмент мог иметь доступ к паблишеру
     *
     * @return publisher
     */
    @Override
    public Publisher getPublisher() {
        return publisher;
    }

}