package com.example.mynotes;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentNoteTitles extends Fragment implements Observer {

    private SimpleNote currentNote;

    public FragmentNoteTitles() {
    }

    public static FragmentNoteTitles newInstance(MySimpleNotesArrayList param1) {
        FragmentNoteTitles fragment = new FragmentNoteTitles();

        Bundle args = new Bundle();
        args.putParcelable(Constants.MY_SIMPLE_ARRAY_LIST, param1);
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

        return inflater.inflate(R.layout.fragment_note_titles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) { //если ничего не сохранено, устанавливаемм заметку по умолчанию
            currentNote = Constants.DEFAULT_NOTE;
        } else {
            currentNote = savedInstanceState.getParcelable(Constants.CURRENT_NOTE);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showLand();
        }

        initView(view); //прорисовка фрагмента на основе массива с заметками

    }

    /**
     * выводим на экран все названия заметок
     *
     * @param view view
     */
    private void initView(View view) {
        // вытаскиваем наш список заметок из аргументов фрагмента
        MySimpleNotesArrayList mySimpleNotesArrayList = getArguments().getParcelable(Constants.MY_SIMPLE_ARRAY_LIST);

        clearBackStack(); //очищаем весь бекстек фрагмент менеджера перед его заполнением, чтобы не дублировать заголовки на экране

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
        outState.putParcelable(Constants.CURRENT_NOTE, currentNote);
    }

    private void showLand() {
        FragmentNoteContent fragmentNoteContent = FragmentNoteContent.newInstance(currentNote);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contents, fragmentNoteContent).commit();
    }

    private void showPort() {
        FragmentNoteContent fragmentNoteContent = FragmentNoteContent.newInstance(currentNote);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.titles, fragmentNoteContent).addToBackStack("").commit();
    }

    /**
     * Полностью очищает и перерисовывает фргмент
     */
    @Override
    public void refresh() {
        View view = getView();
        ((LinearLayout) view).removeAllViews();
        initView(view);
    }

    /**
     * метод полностью очищает бекстек supportFragmentManager
     */
    private void clearBackStack() {
        if (requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = requireActivity().getSupportFragmentManager().getBackStackEntryAt(0);
            requireActivity().getSupportFragmentManager().popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}