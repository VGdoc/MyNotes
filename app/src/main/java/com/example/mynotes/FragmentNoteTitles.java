package com.example.mynotes;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;

public class FragmentNoteTitles extends Fragment {

    private String currentNote;

    public FragmentNoteTitles() {
    }

    public static FragmentNoteTitles newInstance(NotesMainContainer param1) {
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
            currentNote = savedInstanceState.getString(Constants.CURRENT_NOTE);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showLand();
        }

        initView(view); //прорисовка фрагмента на основе массива с заметками
        if (savedInstanceState == null) {
            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragmennt_note_titles_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * выводим на экран все названия заметок
     *
     * @param view view
     */
    private void initView(View view) {
        // вытаскиваем наш список заметок из аргументов фрагмента
        NotesMainContainer notesMainContainer = getArguments().getParcelable(Constants.MY_SIMPLE_ARRAY_LIST);

        clearBackStack(); //очищаем весь бекстек фрагмент менеджера перед его заполнением, чтобы не дублировать заголовки на экране
        String[] noteTitles = NotesMainContainer.getTitles().toArray(new String[0]);

        for (int i = 0; i < noteTitles.length; i++) { // идём по списку с заметками

            int finalI = i;

            TextView textView = new TextView(getContext()); // создаём новый tv
            textView.setTextSize(30f);
            textView.setText(noteTitles[i]); //достаём текщую заметку, устанавливаем размер и текст tv из названия заметки

            ((LinearLayout) view).addView(textView); // добавляем этот tv на лэйаут

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    currentNote = noteTitles[finalI];
                    showCurrentNoteContent();
                }
            });

            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(requireContext(), view);
                    requireActivity().getMenuInflater().inflate(R.menu.note_title_popup, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()) {
                                case (R.id.popup_note_title_edit):
                                    //TODO + снекбар удалить, а значит и текст не надо выносить
                                    Snackbar.make(view, String.format("Заметка \"%s\" редактируется", textView.getText()), Snackbar.LENGTH_LONG).show();
                                    break;
                                case (R.id.popup_note_title_delete):
                                    NotesMainContainer.deleteNote(textView.getText().toString()); // удаляем из БД
                                    ((LinearLayout) requireView()).removeView(textView); // удаляем текущий tv
                                    Snackbar.make(requireView(), String.format(getResources().getString(R.string.note_deleted_message), textView.getText()), Snackbar.LENGTH_LONG).show();
                                    if (textView.getText().equals(currentNote)) { // если удалили текущую заметку
                                        currentNote = Constants.DEFAULT_NOTE; // сбрасываем значение текущей заметки
                                    }

                                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) { // перерисовываем фрагмент
                                        showLand();
                                    }
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
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.CURRENT_NOTE, currentNote);
    }

    private void showLand() {
        FragmentNoteContent fragmentNoteContent = FragmentNoteContent.newInstance(currentNote);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contents, fragmentNoteContent).commit();
    }

    private void showPort() {
        FragmentNoteContent fragmentNoteContent = FragmentNoteContent.newInstance(currentNote);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.titles, fragmentNoteContent).addToBackStack("").commit();
    }


    public void showCurrentNoteContent() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showLand();
        } else {
            showPort();
        }
    }

    /**
     * Полностью очищает и перерисовывает фргмент
     */
    public void refreshContents() {

        ((LinearLayout) requireView()).removeAllViews();
        initView(requireView());
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