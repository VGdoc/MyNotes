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
    ImageView deleteButton;
    ImageView settingsButton;

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
//                        addButtonFunc();
                        break;
                    case (R.id.frame_delete_note_btn):
//                        deleteButtonFunc();
                        break;
                    case (R.id.frame_app_settings_btn):
                        settingsButtonFunc();
                        break;
                }
            }
        };

        returnButton.setOnClickListener(listener);
        addButton.setOnClickListener(listener);
        deleteButton.setOnClickListener(listener);
        settingsButton.setOnClickListener(listener);
    }

    private void settingsButtonFunc() {
        FragmentSettings fragmentSettings = FragmentSettings.newInstance();
        getParentFragmentManager().beginTransaction().add(R.id.main_activity_fullscreen,fragmentSettings).addToBackStack("").commit();
    }

    private void returnButtonFunc() {
        requireActivity().onBackPressed();
    }

    void init(View view) {

        returnButton = requireView().findViewById(R.id.frame_return_btn);
        returnButton.setImageResource(R.drawable.return_button);

        addButton = requireView().findViewById(R.id.frame_add_new_note_btn);
        addButton.setImageResource(R.drawable.add_button);

        deleteButton = requireView().findViewById(R.id.frame_delete_note_btn);
        deleteButton.setImageResource(R.drawable.delete_button);

        settingsButton = requireView().findViewById(R.id.frame_app_settings_btn);
        settingsButton.setImageResource(R.drawable.settings_button);
    }
}