package com.ssaczkowski.mynotes.db;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ssaczkowski.mynotes.db.NoteRoomDataBase;
import com.ssaczkowski.mynotes.db.dao.NoteDao;
import com.ssaczkowski.mynotes.db.entity.NoteEntity;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<NoteEntity>> allNotes;
    private LiveData<List<NoteEntity>> allFavoriteNotes;

    public NoteRepository(Application application) {
        NoteRoomDataBase db = NoteRoomDataBase.getDatabase(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAll();
        allFavoriteNotes = noteDao.getAllFavorites();
    }

    public LiveData<List<NoteEntity>> getAll() {
        return allNotes;
    }

    public LiveData<List<NoteEntity>> getAllFavoriteNotes() {
        return allFavoriteNotes;
    }

    public void insert(NoteEntity note){
        new insertAsyncTask(noteDao).execute(note);
    }

    public void update(NoteEntity note){
        new updateAsyncTask(noteDao).execute(note);
    }

    private static class insertAsyncTask extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDao noteDaoAsyncTask;

        insertAsyncTask(NoteDao dao){
            noteDaoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {

            noteDaoAsyncTask.insert(noteEntities[0]);

            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDao noteDaoAsyncTask;

        updateAsyncTask(NoteDao dao){
            noteDaoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {

            noteDaoAsyncTask.insert(noteEntities[0]);

            return null;
        }
    }

}
