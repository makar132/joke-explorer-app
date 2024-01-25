package com.example.jokeexplorer.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jokeexplorer.data.local.dao.JokeDao
import com.example.jokeexplorer.data.local.entities.JokeEntity

@Database(
    entities = [JokeEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jokeDao() : JokeDao

    companion object {
        @Volatile
        private var instance : AppDatabase? = null
        fun getDatabase(context : Context) : AppDatabase {
            return instance ?: synchronized(this)
            {
                Room.databaseBuilder(
                    context = context,
                    AppDatabase::class.java,
                    "db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}