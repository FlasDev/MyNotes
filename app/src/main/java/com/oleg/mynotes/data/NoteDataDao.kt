package com.oleg.mynotes.data

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
interface NoteDataDao {

    @Query("SELECT * from notesData")
    fun getAllNotes(): List<Note>

    @Query("SELECT * from notesData WHERE id = :id")
    fun getNoteById(id: Long): Note

    @Insert(onConflict = REPLACE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)
}