package com.oleg.mynotes.notedetail

import com.oleg.mynotes.data.Note

interface NoteDetailContract {
    interface View{
        fun setNoteTitle(title: String)
        fun setNoteDetail(detail: String)
    }

    interface UserActionListener{
        fun openNote(id: Long)
    }

}