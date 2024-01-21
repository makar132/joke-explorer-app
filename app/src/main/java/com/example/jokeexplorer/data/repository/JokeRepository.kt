package com.example.jokeexplorer.data.repository

import com.example.jokeexplorer.data.model.Joke
import com.example.jokeexplorer.data.remote.JokeApiService
import retrofit2.awaitResponse

class JokeRepository (private val jokeService: JokeApiService){
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
