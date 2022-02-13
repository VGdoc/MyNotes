package com.example.mynotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentSettings extends Fragment {

    Button themeSettingsButton;
    Button aboutButton;

    public FragmentSettings() {
    }

    public static FragmentSettings newInstance() {
        FragmentSettings fragment = new FragmentSettings();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
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
                    case (R.id.btn_theme_settings):
                        themeSettingsButtonFunc();
                        break;
                    case (R.id.btn_about):
                        aboutButtonFunc();
                        break;
                }
            }
        };

        themeSettingsButton.setOnClickListener(listener);
        aboutButton.setOnClickListener(listener);
    }

    private void aboutButtonFunc() {
        //TODO: открыть фрагмент с окном "О программе"
    }

    private void themeSettingsButtonFunc() {
        //TODO: открыть фрагмент с настройками темы приложения
    }

    void init(View view) {

        themeSettingsButton = requireView().findViewById(R.id.btn_theme_settings);
        aboutButton = requireView().findViewById(R.id.btn_about);
    }
}