package com.oleg.mynotes.addnote

import com.oleg.mynotes.data.Note
import com.oleg.mynotes.data.NoteRepository
import com.oleg.mynotes.utilities.runOnIoThread

class AddNotePresenter(val noteRepository: NoteRepository, val addNotesView: AddNoteContract.View)
    : AddNoteContract.UserActionListener
{
    override fun saveNote(title: String, description: String) {
        if (title.isEmpty()){
            addNotesView.showEmptyNoteError()
        }else{
            runOnIoThread {
                noteRepository.insertNote(Note(null, title, description))
            }
            addNotesView.showNotesList()
        }

    }
}