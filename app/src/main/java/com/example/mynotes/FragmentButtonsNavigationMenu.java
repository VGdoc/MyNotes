package com.example.mynotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FragmentButtonsNavigationMenu extends Fragment {

    ImageView returnButton;
    ImageView addButton;

    public FragmentButtonsNavigationMenu() {
    }


    public static FragmentButtonsNavigationMenu newInstance() {
        FragmentButtonsNavigationMenu fragment = new FragmentButtonsNavigationMenu();
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
        return inflater.inflate(R.layout.fragment_buttons_navigation_menu, container, false);
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
                switch (view.getId()) {
                    case (R.id.frame_return_btn):
                        returnButtonFunc();
                        break;
                    case (R.id.frame_add_new_note_btn):
                        addButtonFunc();
                        break;
                }
            }
        };

        returnButton.setOnClickListener(listener);
        addButton.setOnClickListener(listener);
    }

    private void addButtonFunc() {
        FragmentAddNewNote fragmentAddNewNote = FragmentAddNewNote.newInstance();
        getParentFragmentManager().beginTransaction().add(R.id.main_activity_fullscreen,fragmentAddNewNote).addToBackStack("").commit();
    }

    private void returnButtonFunc() {
        requireActivity().onBackPressed();
    }

    void init(View view) {

        returnButton = requireView().findViewById(R.id.frame_return_btn);
        returnButton.setImageResource(R.drawable.ic_baseline_arrow_back_24);

        addButton = requireView().findViewById(R.id.frame_add_new_note_btn);
        addButton.setImageResource(R.drawable.ic_baseline_add_24);
    }
}