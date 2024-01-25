package com.example.jokeexplorer.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.jokeexplorer.data.local.entities.JokeEntity
import retrofit2.http.DELETE
@Dao
interface JokeDao {
    @Upsert
    suspend fun upsertAll(jokes : List<JokeEntity>)

    @Query("SELECT * FROM JokeEntity")
    fun pagingSource() : PagingSource<Int, JokeEntity>

    @Query("DELETE FROM JokeEntity")
    fun clearAll()
}