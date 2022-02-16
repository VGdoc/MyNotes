package com.example.mynotes;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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

        if (savedInstanceState == null) {
            setHasOptionsMenu(true);
        }

        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(requireContext(), view, Gravity.CENTER);
                requireActivity().getMenuInflater().inflate(R.menu.fragment_note_content_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case (R.id.action_edit_note):
                                //TODO
                                Toast.makeText(requireContext(), "Заметка редактируется", Toast.LENGTH_LONG).show();
                                break;
                            case (R.id.action_share):
                                //TODO
                                Toast.makeText(requireContext(), "Текст заметки отправляется через месенджер", Toast.LENGTH_LONG).show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_note_content_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}