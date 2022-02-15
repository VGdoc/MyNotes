package com.example.mynotes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentNoteContent extends Fragment {

    public FragmentNoteContent() {
    }

    public static FragmentNoteContent newInstance(SimpleNote note) {
        FragmentNoteContent fragment = new FragmentNoteContent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.CURRENT_NOTE, note);
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

        return inflater.inflate(R.layout.fragment_note_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SimpleNote note = getArguments().getParcelable(Constants.CURRENT_NOTE);

        FragmentCurrentNoteTitleChild fragmentCurrentNoteTitleChild = FragmentCurrentNoteTitleChild.newInstance(note);

        //реализация через replace
//        getChildFragmentManager().beginTransaction().replace(R.id.note_title_while_content,fragmentCurrentNoteTitleChild).commit();

        //реализация через add
        getChildFragmentManager().beginTransaction().add(R.id.note_title_while_content, fragmentCurrentNoteTitleChild).commit();

        TextView textView = view.findViewById(R.id.note_contents);
        textView.setTextSize(30f);
        textView.setText(note.getContent());

    }

}