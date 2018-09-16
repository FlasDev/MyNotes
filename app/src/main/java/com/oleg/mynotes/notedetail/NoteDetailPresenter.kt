package com.oleg.mynotes.notedetail

import com.oleg.mynotes.data.Note
import com.oleg.mynotes.data.NoteRepository
import com.oleg.mynotes.utilities.runOnIoThread

class NoteDetailPresenter(val notesRepository: NoteRepository,
                          val noteDetailView: NoteDetailContract.View): NoteDetailContract.UserActionListener {


    override fun openNote(id: Long) {
        runOnIoThread {
            val note = notesRepository.getNoteById(id)
            noteDetailView.setNoteTitle(note.title)
            noteDetailView.setNoteDetail(note.description!!)
        }
    }

}