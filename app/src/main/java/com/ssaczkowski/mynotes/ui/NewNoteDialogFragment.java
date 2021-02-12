package com.ssaczkowski.mynotes.ui;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.ssaczkowski.mynotes.R;
import com.ssaczkowski.mynotes.db.entity.NoteEntity;
import com.ssaczkowski.mynotes.viewmodel.NewNoteDialogViewModel;

public class NewNoteDialogFragment extends DialogFragment {

    private View view;
    private EditText etTitle,etContent;
    private RadioGroup rgColor;
    private Switch swFavoriteNote;

    public static NewNoteDialogFragment newInstance() {
        return new NewNoteDialogFragment();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("New note");
        builder.setMessage("Enter the details of the new note")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String title = etTitle.getText().toString();
                        String content = etContent.getText().toString();
                        int color = R.color.red_light;
                        switch (rgColor.getCheckedRadioButtonId()){
                            case R.id.radioButtonGreen: color = R.color.green_light; break;
                            case R.id.radioButtonBlue: color = R.color.blue_light; break;
                        }

                        boolean isFavorite = swFavoriteNote.isChecked();
                        NewNoteDialogViewModel viewModel = new ViewModelProvider(getActivity()).get(NewNoteDialogViewModel.class);
                        viewModel.insertNote(new NoteEntity(title,content,isFavorite,color));
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.new_note_dialog_fragment,null);

        etTitle = view.findViewById(R.id.editTextTitle);
        etContent = view.findViewById(R.id.multiLineTextContent);

        rgColor = view.findViewById(R.id.radioGroup);
        swFavoriteNote = view.findViewById(R.id.switchFavoriteNote);

        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }

}