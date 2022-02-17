package com.example.mynotes;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Set;

public class NotesMainContainer implements Parcelable {

    private static HashMap<String, String> allNotes;


    public NotesMainContainer(HashMap<String, String> savedList) {

        if (savedList == null) {
            new NotesMainContainer();
        } else
            allNotes = savedList;
    }

    protected NotesMainContainer(Parcel in) {
    }

    public static final Creator<NotesMainContainer> CREATOR = new Creator<NotesMainContainer>() {
        @Override
        public NotesMainContainer createFromParcel(Parcel in) {
            return new NotesMainContainer(in);
        }

        @Override
        public NotesMainContainer[] newArray(int size) {
            return new NotesMainContainer[size];
        }
    };

    public NotesMainContainer() {
        allNotes = new HashMap<>();
    }

    public static boolean contains(String title) {
        return allNotes.containsKey(title);
    }

    /**
     * Метод добавляет новую заметку
     */
    public int addNewNote(String title, String content) {
        allNotes.put(title, content);
        return 0;
    }

    public static String getNoteContent(String noteTitle) {
        return allNotes.get(noteTitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static Set<String> getTitles() {
        return allNotes.keySet();
    }

    public int getLength() {
        return allNotes.size();
    }

    public static void deleteNote(String title) {
        allNotes.remove(title);
    }
}
