package com.example.mynotes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteTitles extends Fragment implements Observer {

    private static final String CURRENT_NOTE = "current_note";
    private static final String MY_SIMPLE_ARRAY_LIST = "my_simple_array_list";
    private static final SimpleNote DEFAULT_NOTE =
            new SimpleNote("Заметок ещё нет", "Заметки не выбраны");
    private SimpleNote currentNote;

    public NoteTitles() {
        // Required empty public constructor
    }

    public static NoteTitles newInstance(MySimpleNotesArrayList param1) {
        NoteTitles fragment = new NoteTitles();

        Bundle args = new Bundle();
        args.putParcelable(MY_SIMPLE_ARRAY_LIST, param1);
        fragment.setArguments(args);

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
        return inflater.inflate(R.layout.fragment_note_titles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            currentNote = DEFAULT_NOTE;
        } else {
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showLand();
        }

        initView(view);

    }

    /**
     * выводим на экран все названия заметок
     *
     * @param view
     */
    private void initView(View view) {
        // вытаскиваем наш список заметок из аргументов фрагмента
        MySimpleNotesArrayList mySimpleNotesArrayList = getArguments().getParcelable(MY_SIMPLE_ARRAY_LIST);


        for (int i = 0; i < mySimpleNotesArrayList.getLength(); i++) { // идём по списку с заметками

            int finalI = i;

            TextView textView = new TextView(getContext()); // создаём новый tv
            textView.setTextSize(30f);
            textView.setText(mySimpleNotesArrayList.getNote(i).getTitle()); //достаём текщую заметку, устанавливаем размер и текст tv из названия заметки

            ((LinearLayout) view).addView(textView); // добавляем этот tv на лэйаут


            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    currentNote = mySimpleNotesArrayList.getNote(finalI);
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        showLand();
                    } else {
                        showPort();
                    }
                }
            });
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CURRENT_NOTE, currentNote);
    }

    private void showLand() {
        NoteContent noteContent = NoteContent.newInstance(currentNote);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contents, noteContent).commit();
    }

    private void showPort() {
        NoteContent noteContent = NoteContent.newInstance(currentNote);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.titles, noteContent).addToBackStack("").commit();
    }

    @Override
    public void refresh() {
        View view = getView();
        ((LinearLayout)view).removeAllViews();
        initView(view);
    }
}