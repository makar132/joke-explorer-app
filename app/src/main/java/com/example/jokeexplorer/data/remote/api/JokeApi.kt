package com.example.jokeexplorer.data.remote.api

import com.example.jokeexplorer.domain.Joke
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface JokeApi {

    @Headers("X-RapidAPI-Key: 4f0fe32599msh71ca95fa54916ecp15eacejsnd9de37564233",
        "X-RapidAPI-Host: jokeapi-v2.p.rapidapi.com")
    @GET("joke/Any?format=json")
    suspend fun getJokesPage(
        @Query("amount") amount: Int = 10,
        @Query("idRange") idRange : String
        ): JokesPageResponse

    companion object
    {
        const val BASE_URL="https://jokeapi-v2.p.rapidapi.com/"
    }

}