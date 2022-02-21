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
import android.widget.Toast;


public class FragmentAddNewNote extends Fragment {

    Button btnSave, btnCancel;
    EditText editTextTitle, editTextContent;
    NeedViewRefreshListener interfacePartOfFragment;

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
                        if (editTextTitle.getText().toString().equals("")){ // Если строка пустая
                            Toast.makeText(requireContext(),R.string.empty_title_warning_message,Toast.LENGTH_LONG).show();// тут тост
                            break;
                        }
                        if (NotesMainContainer.contains(editTextTitle.getText().toString())){ // если такая заметка уже существует
                            Toast.makeText(requireContext(),R.string.note_already_exist,Toast.LENGTH_LONG).show(); // тут тост
                            break;
                        }
                        MainActivity.notesList.addNewNote(editTextTitle.getText().toString(), editTextContent.getText().toString());
                        refreshViews(interfacePartOfFragment);
                        //TODO: сделать сохранение состояния при повороте экрана
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

    /**
     * В методе рассылаются команды всем вью, в которых произошли изменения, чтобы они обновились.
     * Реализовано с помощью интерфейса NeedViewRefreshListener
     */
    private void refreshViews(NeedViewRefreshListener interfacePartOfFragment) {
        if (interfacePartOfFragment != null){
            interfacePartOfFragment.refreshView();
        } else {
            requireActivity().onBackPressed(); // чтобы закрыть фрагмент создания новой заметки
        }
    }

    private void init(View view) {
        btnCancel = view.findViewById(R.id.btn_cancel_add_new_note);
        btnSave = view.findViewById(R.id.btn_save_add_new_note);
        editTextContent = view.findViewById(R.id.add_new_note_content_edit_text);
        editTextTitle = view.findViewById(R.id.add_new_note_title_edit_text);

        if (requireActivity().getSupportFragmentManager().findFragmentById(R.id.titles) != null &&
                requireActivity().getSupportFragmentManager().findFragmentById(R.id.titles) instanceof FragmentNoteTitles){

            interfacePartOfFragment = (FragmentNoteTitles) requireActivity().getSupportFragmentManager().findFragmentById(R.id.titles);
        }
    }
}