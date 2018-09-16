package com.oleg.mynotes.data

class NoteRepository private constructor(
        private val noteDataDao: NoteDataDao
){
    fun getAllNotes() = noteDataDao.getAllNotes()

    fun getNoteById(id: Long) = noteDataDao.getNoteById(id)

    fun insertNote(note: Note) = noteDataDao.insert(note)

    fun updateNote(note: Note) = noteDataDao.update(note)

    fun deleteNote(note: Note) = noteDataDao.delete(note)

    companion object {
        @Volatile private var instance: NoteRepository? = null

        fun getInstance(noteDataDao: NoteDataDao) =
                instance?: synchronized(this){
                    instance?: NoteRepository(noteDataDao).also {
                        instance = it
                    }
                }
    }

}