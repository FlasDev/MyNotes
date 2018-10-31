package com.oleg.mynotes.notes

import android.util.Log
import android.view.View
import com.oleg.mynotes.data.Note
import com.oleg.mynotes.data.NoteRepository
import com.oleg.mynotes.utilities.runOnIoThread

class NotesPresenter(val noteRepository: NoteRepository, val notesView: NotesContract.View)
    : NotesContract.UserActionsListener {

    override fun deleteNote(requestNote: Note) {
        runOnIoThread {
            noteRepository.deleteNote(requestNote)
            notesView.showDeleteNote(requestNote)
            loadNotes()
        }

    }

    override fun openNoteDetail(requestNote: Note) {
        notesView.showNoteDetailUi(requestNote.id!!)
    }

    override fun loadNotes() {
        runOnIoThread {
            val notesList = noteRepository.getAllNotes()
            if (notesList.isEmpty()){
                notesView.showEmptyNoteListVisibility(View.VISIBLE)
            }else {
                notesView.showEmptyNoteListVisibility(View.INVISIBLE)
            }

            notesView.showNotes(noteRepository.getAllNotes())
        }
    }

    override fun findNote(title: String) {
        runOnIoThread {
            val notes = noteRepository.getAllNotes()
            val findNotes = mutableListOf<Note>()
            for (note in notes){
                if (note.title.toLowerCase().contains(title.toLowerCase())){
                    findNotes.add(note)
                }
            }
            notesView.showNotes(findNotes)
        }
    }

    override fun addNewNote() {
        notesView.showAddNote()
    }

    override fun returnDeletedNote(note: Note) {
        runOnIoThread {
            noteRepository.insertNote(note)
            loadNotes()
        }
    }
}