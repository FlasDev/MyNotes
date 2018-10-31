package com.oleg.mynotes.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.oleg.mynotes.utilities.DATABASE_NAME

@Database(entities = [Note::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase(){
    abstract fun noteDataDao(): NoteDataDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return instance?: synchronized(this){
                instance?: buildDatabase(context)
                        .also {
                            instance = it
                        }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase{
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
        }


    }

}