package com.example.jokeexplorer.data.repository

import androidx.paging.PagingSource
import com.example.jokeexplorer.data.local.dao.JokeDao
import com.example.jokeexplorer.data.local.entities.JokeEntity
import com.example.jokeexplorer.data.model.Joke
import com.example.jokeexplorer.data.remote.api.JokeApi
import retrofit2.awaitResponse

class JokeRepository (private val jokeService: JokeApi,private val dao : JokeDao){
    suspend fun getRandomJoke(): Joke? {

        val response = jokeService.getRandomJoke().awaitResponse()
        return when {
            response.isSuccessful -> {
                response.body()
            }

            else -> {
                // Handle API errors
                null
            }
        }
    }


}
