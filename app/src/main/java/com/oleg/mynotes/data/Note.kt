package com.oleg.mynotes.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.Nullable

@Entity(tableName = "notesData")
data class Note(
        @PrimaryKey(autoGenerate = true) val id: Long?,
        @ColumnInfo val title: String,
        @ColumnInfo val description: String?,
        @ColumnInfo var date: Date? = Date()
)