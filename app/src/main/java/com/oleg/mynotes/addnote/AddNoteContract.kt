package com.oleg.mynotes.addnote

import com.oleg.mynotes.data.Note

interface AddNoteContract {
    interface View{
        fun showNotesList()
        fun showEmptyNoteError()
    }

    interface UserActionListener{
        fun saveNote(title: String, description: String)
    }
}