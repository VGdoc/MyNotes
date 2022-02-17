package com.example.mynotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FragmentSettings extends Fragment {

    Button themeSettingsButton;

    public FragmentSettings() {
    }

    public static FragmentSettings newInstance() {
        return new FragmentSettings();
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
                }
            }
        };

        themeSettingsButton.setOnClickListener(listener);
    }

    private void themeSettingsButtonFunc() {
        //TODO: открыть фрагмент с настройками темы приложения
    }

    void init(View view) {
        themeSettingsButton = view.findViewById(R.id.btn_theme_settings);
    }
}