package com.example.mynotes;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleNote implements Parcelable {

    private String title;
    private String content;

    public SimpleNote(String title, String content) {
        this.title = title;
        this.content = content;
    }

    protected SimpleNote(Parcel in) {
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<SimpleNote> CREATOR = new Creator<SimpleNote>() {
        @Override
        public SimpleNote createFromParcel(Parcel in) {
            return new SimpleNote(in);
        }

        @Override
        public SimpleNote[] newArray(int size) {
            return new SimpleNote[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(content);
    }
}
