package com.oleg.mynotes.notes

import com.oleg.mynotes.data.Note

interface NotesContract {

    interface View{
        fun showNotes(note: List<Note>)
        fun showAddNote()
        fun showNoteDetailUi(noteId: Long)
        fun showDeleteNote(note: Note)
        fun showEmptyNoteListVisibility(visibility: Int)
    }

    interface UserActionsListener{
        fun loadNotes()
        fun addNewNote()
        fun openNoteDetail(requestNote: Note)
        fun deleteNote(requestNote: Note)
        fun findNote(title: String)
        fun returnDeletedNote(note: Note)
    }
}