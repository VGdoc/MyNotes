package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static MySimpleNotesArrayList testNotes = new MySimpleNotesArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleNote testNewNote = new SimpleNote("test note", "test content");
        testNotes.addNewNote(testNewNote);
        testNewNote = new SimpleNote("test note 2", "test content 2");
        testNotes.addNewNote(testNewNote);

        NoteTitles noteTitles = NoteTitles.newInstance(testNotes);
        getSupportFragmentManager().beginTransaction().add(R.id.titles,noteTitles).commit();
    }
}