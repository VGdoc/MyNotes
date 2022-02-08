package com.example.mynotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NoteTitles extends Fragment {

    private static final String CURRENT_NOTE = "current_note";
    private SimpleNote currentNote;

    public NoteTitles() {
        // Required empty public constructor
    }

    public static NoteTitles newInstance(MySimpleNotesArrayList param1) {
        NoteTitles fragment = new NoteTitles();
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_NOTE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//            currentNote = getArguments().getParcelable(CURRENT_NOTE);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_titles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MySimpleNotesArrayList tempList = MainActivity.testNotes; //todo брать этот список не из активити
        currentNote = tempList.getNote(0);
        TextView textView = new TextView(getContext());
        textView.setTextSize(30f);
        textView.setText(currentNote.getTitle());

        ((LinearLayout)view).addView(textView);

    }
}