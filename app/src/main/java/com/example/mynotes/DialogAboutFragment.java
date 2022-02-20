package com.example.mynotes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogAboutFragment extends DialogFragment {

    private static final String APPLICATION_VERSION = "0.0.6";
    private static final String APPLICATION_CREATOR = "Vladimir Grigoriev";
    private static AlertDialog alertDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = getLayoutInflater().inflate(R.layout.fragment_dialog_about, null);
        initElements(view);

        alertDialog = new AlertDialog.Builder(requireContext()).setView(view).show();

        return alertDialog;
    }

    private void initElements(View view) {

        TextView version = view.findViewById(R.id.about_dialog_version);
        version.setText(APPLICATION_VERSION);

        TextView creator = view.findViewById(R.id.about_dialog_creator);
        creator.setText(APPLICATION_CREATOR);

        view.findViewById(R.id.btn_about_dialog_ok).setOnClickListener(view1 -> alertDialog.dismiss());
    }
}
