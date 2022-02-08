package com.example.mynotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NoteTitles extends Fragment {

    private static final String CURRENT_NOTE = "current_note";
    private static final String MY_SIMPLE_ARRAY_LIST = "my_simple_array_list";
    private SimpleNote currentNote;;

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

//        if (getArguments() != null) {
//            currentNote = getArguments().getParcelable(CURRENT_NOTE);
//        }
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

        initView(view);

    }

    /**
     * выводим на экран все названия заметок
     * @param view
     */
    private void initView(View view) {
        // вытаскиваем наш список заметок из аргументов фрагмента
        MySimpleNotesArrayList mySimpleNotesArrayList = getArguments().getParcelable(MY_SIMPLE_ARRAY_LIST);

        for (int i = 0; i < mySimpleNotesArrayList.getLength(); i++){ // идём по списку с заметками

            currentNote = mySimpleNotesArrayList.getNote(i);  //достаём текщую заметку
            TextView textView = new TextView(getContext()); // создаём новый tv
            textView.setTextSize(30f);
            textView.setText(currentNote.getTitle());  // устанавливаем размер и текст tv из названия заметки

            ((LinearLayout) view).addView(textView); // добавляем этот tv на лэйаут
        }
    }
}