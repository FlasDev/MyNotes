package com.oleg.mynotes.utilities

import android.content.Context
import com.oleg.mynotes.data.AppDatabase
import com.oleg.mynotes.data.NoteRepository

object InjectorsUntils {

    fun provideNoteRepository(context: Context): NoteRepository{
        val dao = getNoteDataDao(context)
        val noteRepository = NoteRepository.getInstance(dao)
        return noteRepository
    }

    private fun getNoteDataDao(context: Context) =
            AppDatabase.getInstance(context).noteDataDao()
}