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
    abstract val dao : JokeDao

}