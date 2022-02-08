package com.example.mynotes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MySimpleNotesArrayList implements Parcelable {

    private static ArrayList<SimpleNote> notesArrayList;

    public MySimpleNotesArrayList(ArrayList<SimpleNote> savedList) {

        if (savedList == null) {
            new MySimpleNotesArrayList();
        } else
            notesArrayList = savedList;
    }

    protected MySimpleNotesArrayList(Parcel in) {
    }

    public static final Creator<MySimpleNotesArrayList> CREATOR = new Creator<MySimpleNotesArrayList>() {
        @Override
        public MySimpleNotesArrayList createFromParcel(Parcel in) {
            return new MySimpleNotesArrayList(in);
        }

        @Override
        public MySimpleNotesArrayList[] newArray(int size) {
            return new MySimpleNotesArrayList[size];
        }
    };

    public MySimpleNotesArrayList() {
        notesArrayList = new ArrayList<>();
    }

    /**
     * Метод добавляет новую заметку
     *
     * @param note заметка
     * @return 0 если успешно //TODO обработать случай, когда такая заметка уже существует
     */
    public int addNewNote(SimpleNote note) {
        notesArrayList.add(note);
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public SimpleNote getNote(int index) {
        return notesArrayList.get(index); //todo сделать проверку на выход за границы
    }

    public int getLength() {
        return notesArrayList.size();
    }
}
