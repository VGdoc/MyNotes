package com.example.mynotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentAddNewNote extends Fragment {

    Button btnSave, btnCancel;
    EditText editTextTitle, editTextContent;

    public FragmentAddNewNote() {
    }

    public static FragmentAddNewNote newInstance() {
        return new FragmentAddNewNote();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        setListeners();
    }

    private void setListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case (R.id.btn_save_add_new_note):
                        break;
                    case (R.id.btn_cancel_add_new_note):
                        requireActivity().onBackPressed();
                        break;
                }
            }
        };

        btnSave.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);
    }

    private void init(View view) {
        btnCancel = view.findViewById(R.id.btn_cancel_add_new_note);
        btnSave = view.findViewById(R.id.btn_save_add_new_note);
        editTextContent = view.findViewById(R.id.add_new_note_content_edit_text);
        editTextTitle = view.findViewById(R.id.add_new_note_title_edit_text);
    }
}