package com.ssaczkowski.mynotes;

public interface NoteInteractionListener {

    void editNoteClick(Note note);
    void deleteNoteClick(Note note);
    void favoriteNoteClick(Note note);

}
