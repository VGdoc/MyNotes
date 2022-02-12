package com.example.mynotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentCurrentNoteTitleChild extends Fragment {

    public FragmentCurrentNoteTitleChild() {
    }

    public static FragmentCurrentNoteTitleChild newInstance(SimpleNote note) {
        FragmentCurrentNoteTitleChild fragment = new FragmentCurrentNoteTitleChild();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.CURRENT_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current_note_title_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SimpleNote note = getArguments().getParcelable(Constants.CURRENT_NOTE);

        TextView titleTextView = view.findViewById(R.id.current_note_title);
        titleTextView.setText(note.getTitle());
    }
}