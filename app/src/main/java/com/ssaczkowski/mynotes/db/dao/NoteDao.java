package com.ssaczkowski.mynotes.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ssaczkowski.mynotes.db.entity.NoteEntity;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteEntity noteEntity);

    @Update
    void update(NoteEntity noteEntity);

    @Query("DELETE FROM notes")
    void deleteAll();

    @Query("DELETE FROM notes WHERE  id = :idNote")
    void deleteAll(int idNote);

    @Query("SELECT * FROM notes ORDER BY title ASC")
    LiveData<List<NoteEntity>> getAll();

    @Query("SELECT * FROM notes WHERE isFavorite LIKE 'true'")
    LiveData<List<NoteEntity>> getAllFavorites();


}
