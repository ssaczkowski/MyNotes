package com.ssaczkowski.mynotes.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ssaczkowski.mynotes.db.dao.NoteDao;
import com.ssaczkowski.mynotes.db.entity.NoteEntity;


@Database(entities = {NoteEntity.class}, version = 1)
public abstract class NoteRoomDataBase extends RoomDatabase {
    public abstract NoteDao noteDao();
    private static volatile NoteRoomDataBase INSTANCE;

    public static NoteRoomDataBase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (NoteRoomDataBase.class){
                if(INSTANCE == null){
                    INSTANCE  = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDataBase.class,"notes_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
