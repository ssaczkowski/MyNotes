package com.ssaczkowski.mynotes.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ssaczkowski.mynotes.db.NoteRepository;
import com.ssaczkowski.mynotes.db.entity.NoteEntity;

import java.util.List;

public class NewNoteDialogViewModel extends AndroidViewModel {
    private LiveData<List<NoteEntity>> allNotes;
    private NoteRepository noteRepository;

    public NewNoteDialogViewModel(Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAll();
    }

    public LiveData<List<NoteEntity>> getAllNotes() {
        return allNotes;
    }

    public void insertNote(NoteEntity newNoteEntity){
        noteRepository.insert(newNoteEntity);
    }

    public void updateNote(NoteEntity newNoteEntity){
        noteRepository.update(newNoteEntity);
    }

}