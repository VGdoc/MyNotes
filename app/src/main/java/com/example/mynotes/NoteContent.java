package com.example.mynotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteContent extends Fragment {

    private static final String CURRENT_NOTE = "current_note";

    public NoteContent() {
        // Required empty public constructor
    }

    public static NoteContent newInstance(SimpleNote note) {
        NoteContent fragment = new NoteContent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(CURRENT_NOTE,note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SimpleNote note = getArguments().getParcelable(CURRENT_NOTE);

        TextView textView = view.findViewById(R.id.note_contents);
        textView.setTextSize(30f);
        textView.setText(note.getContent());

    }
}